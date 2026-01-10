package abby.absent.fluff.entities.animations;


import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class AlpacaAnimations {
        public static final Animation WALK = Animation.Builder.create(1.0F).looping()
                .addBoneAnimation("blLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -6.67F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("flLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("brLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("frLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final Animation EAT_GRASS = Animation.Builder.create(1.5F)
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-3.799F, 0.0744F, -142.6143F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5833F, AnimationHelper.createRotationalVector(-6.299F, 0.0744F, -142.6143F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.7083F, AnimationHelper.createRotationalVector(-2.9602F, 0.2037F, -140.1176F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.875F, AnimationHelper.createRotationalVector(-1.6921F, 0.0744F, -142.6143F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.9167F, AnimationHelper.createRotationalVector(0.1056F, 0.0744F, -142.6143F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-3.799F, 0.0744F, -142.6143F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.2917F, AnimationHelper.createTranslationalVector(-1.0F, -1.75F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -3.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -3.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.25F, AnimationHelper.createTranslationalVector(-1.0F, -1.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final Animation SLEEP = Animation.Builder.create(1.0F)
                .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-1.67F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(87.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("body", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -4.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -10.0F, -12.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(15.1044F, -7.3212F, -1.6322F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("blLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -60.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -30.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("flLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 60.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 35.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("brLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -52.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-37.5F, 0.0F, -7.5F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("frLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 50.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-44.9727F, -1.7675F, -1.7683F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final Animation STAND_UP = Animation.Builder.create(1.75F)
                .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(87.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(65.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(25.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("body", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -10.0F, -12.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, -2.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(15.1044F, -7.3212F, -1.6322F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-52.3956F, -7.3212F, -1.6322F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(-27.3956F, -7.3212F, -1.6322F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("blLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -30.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.0833F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, -30.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-15.8889F, -9.6158F, -59.3156F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("flLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 35.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.0833F, AnimationHelper.createRotationalVector(-20.0F, 0.0F, 35.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-68.9673F, 54.105F, 35.9956F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("brLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-37.5F, 0.0F, -7.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-43.3964F, 0.0F, -7.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("frLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-44.9727F, -1.7675F, -1.7683F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-43.3933F, -1.7675F, -1.7683F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("alpaca", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final Animation IDLE = Animation.Builder.create(2.0F)
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.6574F, -7.4713F, -5.0429F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("flLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -12.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.625F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -12.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.125F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("flLeg", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(-1.0F, 2.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.625F, AnimationHelper.createTranslationalVector(-1.0F, 2.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.125F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("brLeg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.2083F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -20.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.375F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -42.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -42.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("brLeg", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.2083F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.375F, AnimationHelper.createTranslationalVector(2.0F, 2.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(2.0F, 2.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();
}