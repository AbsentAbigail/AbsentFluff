package abby.absent.fluff.entities;

import abby.absent.fluff.Utility;
import abby.absent.fluff.WoolColour;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.entities.goals.AlpacaEatGoal;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AlpacaEntity extends AnimalEntity implements Shearable {
    private static final TrackedData<Byte> COLOR = DataTracker.registerData(AlpacaEntity.class, TrackedDataHandlerRegistry.BYTE);
    private AlpacaEatGoal eatBlockGoal;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState eatAnimationState = new AnimationState();
    public boolean eating = false;
    private int idleAnimationTimeout = 0;
    private static final Map<DyeColor, ItemConvertible> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (map) -> {
        map.put(DyeColor.WHITE, ModBlocks.WOOL_BLOCKS.get(WoolColour.WHITE));
        map.put(DyeColor.ORANGE, ModBlocks.WOOL_BLOCKS.get(WoolColour.ORANGE));
        map.put(DyeColor.MAGENTA, ModBlocks.WOOL_BLOCKS.get(WoolColour.MAGENTA));
        map.put(DyeColor.LIGHT_BLUE, ModBlocks.WOOL_BLOCKS.get(WoolColour.LIGHT_BLUE));
        map.put(DyeColor.YELLOW, ModBlocks.WOOL_BLOCKS.get(WoolColour.YELLOW));
        map.put(DyeColor.LIME, ModBlocks.WOOL_BLOCKS.get(WoolColour.LIME));
        map.put(DyeColor.PINK, ModBlocks.WOOL_BLOCKS.get(WoolColour.PINK));
        map.put(DyeColor.GRAY, ModBlocks.WOOL_BLOCKS.get(WoolColour.GRAY));
        map.put(DyeColor.LIGHT_GRAY, ModBlocks.WOOL_BLOCKS.get(WoolColour.LIGHT_GRAY));
        map.put(DyeColor.CYAN, ModBlocks.WOOL_BLOCKS.get(WoolColour.CYAN));
        map.put(DyeColor.PURPLE, ModBlocks.WOOL_BLOCKS.get(WoolColour.PURPLE));
        map.put(DyeColor.BLUE, ModBlocks.WOOL_BLOCKS.get(WoolColour.BLUE));
        map.put(DyeColor.BROWN, ModBlocks.WOOL_BLOCKS.get(WoolColour.BROWN));
        map.put(DyeColor.GREEN, ModBlocks.WOOL_BLOCKS.get(WoolColour.GREEN));
        map.put(DyeColor.RED, ModBlocks.WOOL_BLOCKS.get(WoolColour.RED));
        map.put(DyeColor.BLACK, ModBlocks.WOOL_BLOCKS.get(WoolColour.BLACK));
    });
    private static final Map<TagKey<Item>, DyeColor> DYE_TAGS = Util.make(Maps.newHashMap(), (map) -> {
        map.put(ConventionalItemTags.WHITE_DYES, DyeColor.WHITE);
        map.put(ConventionalItemTags.ORANGE_DYES, DyeColor.ORANGE);
        map.put(ConventionalItemTags.MAGENTA_DYES, DyeColor.MAGENTA);
        map.put(ConventionalItemTags.LIGHT_BLUE_DYES, DyeColor.LIGHT_BLUE);
        map.put(ConventionalItemTags.YELLOW_DYES, DyeColor.YELLOW);
        map.put(ConventionalItemTags.LIME_DYES, DyeColor.LIME);
        map.put(ConventionalItemTags.PINK_DYES, DyeColor.PINK);
        map.put(ConventionalItemTags.GRAY_DYES, DyeColor.GRAY);
        map.put(ConventionalItemTags.LIGHT_GRAY_DYES, DyeColor.LIGHT_GRAY);
        map.put(ConventionalItemTags.CYAN_DYES, DyeColor.CYAN);
        map.put(ConventionalItemTags.PURPLE_DYES, DyeColor.PURPLE);
        map.put(ConventionalItemTags.BLUE_DYES, DyeColor.BLUE);
        map.put(ConventionalItemTags.BROWN_DYES, DyeColor.BROWN);
        map.put(ConventionalItemTags.GREEN_DYES, DyeColor.GREEN);
        map.put(ConventionalItemTags.RED_DYES, DyeColor.RED);
        map.put(ConventionalItemTags.BLACK_DYES, DyeColor.BLACK);
    });

    public AlpacaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR, (byte)0);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) { // TODO: Alpaca food tag
        return stack.streamTags().anyMatch(t -> t.equals(ConventionalItemTags.ANIMAL_FOODS));
    }

    @Override
    public void tick() {
        super.tick();

        if (!getWorld().isClient())
            return;

        updateAnimations();
    }

    private void updateAnimations() {
        if (idleAnimationTimeout <= 0) {
            idleAnimationTimeout = random.nextInt(4 * 20) + 12 * 20;
            idleAnimationState.start(age);
        } else
            --idleAnimationTimeout;

        if (eating) {
            Utility.LOGGER.info("eat2");
            eatAnimationState.setRunning(eatBlockGoal.eatTicks > 0, age);
            eating = false;
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.isOf(Items.BUCKET) && !isBaby()) {
            return actionMilk(player, hand, itemstack);
        }

        if (itemstack.isIn(ConventionalItemTags.SHEAR_TOOLS)) {
            return actionShear(player, hand, itemstack);
        }

        Optional<TagKey<Item>> dyeKey = itemstack.streamTags().filter(DYE_TAGS::containsKey).findFirst();
        if (dyeKey.isPresent()) {
            return actionDye(player, dyeKey.get(), itemstack);
        }

        if (itemstack.isEmpty() && getWorld().isClient) {
            Utility.LOGGER.info("Pet the paca");
            petParticles();
        }

        return super.interactMob(player, hand);
    }

    private @NotNull ActionResult actionDye(PlayerEntity player, TagKey<Item> dyeKey, ItemStack itemstack) {
        DyeColor dyeColor = DYE_TAGS.get(dyeKey);

        if (isSheared() || getColor() == dyeColor) {
            return ActionResult.PASS;
        }

        getWorld().playSound(player, getBlockPos(), SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (getWorld().isClient) {
            return ActionResult.CONSUME;
        }
        setColor(dyeColor);
        itemstack.decrement(1);

        return ActionResult.SUCCESS;
    }

    private @NotNull ActionResult actionShear(PlayerEntity player, Hand hand, ItemStack itemstack) {
        if (this.getWorld().isClient || !this.isShearable())
            return ActionResult.CONSUME;

        this.sheared(SoundCategory.PLAYERS);
        this.emitGameEvent(GameEvent.SHEAR, player);
        itemstack.damage(1, player, getSlotForHand(hand));
        return ActionResult.SUCCESS;
    }

    private @NotNull ActionResult actionMilk(PlayerEntity player, Hand hand, ItemStack itemstack) {
        if (getWorld().isClient)
            return ActionResult.CONSUME;

        player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
        ItemStack milkStack = ItemUsage.exchangeStack(itemstack, player, Items.MILK_BUCKET.getDefaultStack());
        player.setStackInHand(hand, milkStack);
        return ActionResult.SUCCESS;
    }

    private void petParticles() {
        Random random = new Random();
        double xPos = getEyePos().x;
        double yPos = getEyePos().y;
        double zPos = getEyePos().z;
        int count = random.nextInt(5);
        float spread = 1f;
        for (int i = 0; i < count; i++) {
            double x = xPos - spread + random.nextFloat(spread * 2);
            double y = yPos - spread + random.nextFloat(spread * 2);
            double z = zPos - spread + random.nextFloat(spread * 2);

            getWorld().addParticle(ParticleTypes.HEART, x, y, z, 1f, 1f, 1f);
        }
    }

    protected void updateLimbs(float partialTick) {
        float f;
        if (getPose() == EntityPose.STANDING)
            f = Math.min(partialTick * 6f, 1f);
        else
            f = 0;

        limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    protected void initGoals() {
        eatBlockGoal = new AlpacaEatGoal(this, BlockTags.DIRT);

        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        goalSelector.add(2, new AnimalMateGoal(this, 1.1f));
        goalSelector.add(3, new TemptGoal(this, 1.1f, Ingredient.ofItems(Items.CARROT, Items.WHEAT), false));
        goalSelector.add(4, new FollowParentGoal(this, 1.1f));
        goalSelector.add(5, eatBlockGoal);
        goalSelector.add(6, new WanderAroundFarGoal(this, 1f));
        goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6f));
        goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAlpacaAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10f);
    }

    @Nullable
    public AlpacaEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        AlpacaEntity alpacaEntity = ModEntities.ALPACA.create(serverWorld);
        if (alpacaEntity != null) {
            alpacaEntity.setColor(this.getChildColor(this, (AlpacaEntity)passiveEntity));
        }

        return alpacaEntity;
    }

    public void setColor(DyeColor color) {
        byte b = this.dataTracker.get(COLOR);
        this.dataTracker.set(COLOR, (byte)(b & 240 | color.getId() & 15));
    }

    public DyeColor getColor() {
        return DyeColor.byId(this.dataTracker.get(COLOR) & 15);
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            dropWool();
        }
    }

    private void dropWool() {
        ItemEntity itemEntity = this.dropItem(ITEM_BY_DYE.get(this.getColor()), 1);
        if (itemEntity == null) {
            return;
        }

        itemEntity.setVelocity(itemEntity.getVelocity().add(
                ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F),
                (this.random.nextFloat() * 0.05F),
                ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)
        ));
    }

    public boolean isShearable() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sheared", this.isSheared());
        nbt.putByte("Color", (byte)this.getColor().getId());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSheared(nbt.getBoolean("Sheared"));
        this.setColor(DyeColor.byId(nbt.getByte("Color")));
    }

    public boolean isSheared() {
        return (this.dataTracker.get(COLOR) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b = this.dataTracker.get(COLOR);
        if (sheared) {
            this.dataTracker.set(COLOR, (byte)(b | 16));
        } else {
            this.dataTracker.set(COLOR, (byte)(b & -17));
        }
    }

    private DyeColor getChildColor(AnimalEntity firstParent, AnimalEntity secondParent) {
        DyeColor dye1 = ((AlpacaEntity)firstParent).getColor();
        DyeColor dye2 = ((AlpacaEntity)secondParent).getColor();
        CraftingRecipeInput craftingRecipeInput = createChildColorRecipeInput(dye1, dye2);
        Optional<Item> optionalDyeItem = this.getWorld().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingRecipeInput, this.getWorld()).map((recipe) -> recipe.value().craft(craftingRecipeInput, this.getWorld().getRegistryManager())).map(ItemStack::getItem);
        Objects.requireNonNull(DyeItem.class);
        optionalDyeItem = optionalDyeItem.filter(DyeItem.class::isInstance);
        Objects.requireNonNull(DyeItem.class);
        return optionalDyeItem.map(DyeItem.class::cast).map(DyeItem::getColor)
                .orElseGet(() -> this.getWorld().random.nextBoolean() ? dye1 : dye2);
    }

    private static CraftingRecipeInput createChildColorRecipeInput(DyeColor firstColor, DyeColor secondColor) {
        return CraftingRecipeInput.create(2, 1,
                List.of(new ItemStack(DyeItem.byColor(firstColor)), new ItemStack(DyeItem.byColor(secondColor))));
    }

    public void onEatingGrass() {
        super.onEatingGrass();
        this.setSheared(false);
        if (this.isBaby()) {
            this.growUp(60);
        }
    }
}
