package abby.absent.fluff.blocks;

import abby.absent.fluff.Constants;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public enum Pride {
    AGENDER("Agender", ModelTemplate.P14),
    ALTERSEX("Altersex", ModelTemplate.P15),
    AROMANTIC("Aromantic", ModelTemplate.P15),
    ASEXUAL("Asexual", ModelTemplate.P16),
    BISEXUAL("Bisexual", ModelTemplate.P15),
    DEMIBOY("Demiboy", ModelTemplate.P14),
    DEMIGIRL("Demigirl", ModelTemplate.P14),
    DEMIROMANTIC("Demiromantic", ModelTemplate.P15),
    DEMIROMANTIC_EXTENSION("Demiromantic", ModelTemplate.P15),
    DEMISEXUAL("Demisexual", ModelTemplate.P15),
    DEMISEXUAL_EXTENSION("Demisexual", ModelTemplate.P15),
    GENDERFLUID("Genderfluid", ModelTemplate.P15),
    INTERSEX("Intersex", ModelTemplate.P16),
    LESBIAN("Lesbian", ModelTemplate.P15),
    NONBINARY("Non Binary", ModelTemplate.P16),
    PANSEXUAL("Pansexual", ModelTemplate.P15),
    PRIDE("Pride", ModelTemplate.P12),
    PRIDE_PROGRESS("Progress Pride", ModelTemplate.P12),
    TRANSGENDER("Transgender", ModelTemplate.P15),
    TRANSFEM1("Transfem", ModelTemplate.P14),
    TRANSFEM2("Transfem", ModelTemplate.P16),
    TRANSMASC1("Transmasc", ModelTemplate.P14),
    TRANSMASC2("Transmasc", ModelTemplate.P16),
    VINCIAN("Vincian", ModelTemplate.P15),
    POLYAMORY("Polyamory", ModelTemplate.P15),
    POLYAMORY_EXTENSION("Polyamory", ModelTemplate.P15),
    BLACK_LIVES_MATTER("Black Lives Matter", ModelTemplate.P15);

    public final String displayName;
    public final ModelTemplate modelTemplate;

    Pride(String displayName, ModelTemplate modelTemplate) {
        this.displayName = displayName;
        this.modelTemplate = modelTemplate;
    }

    public enum ModelTemplate {
        P12(Constants.MODID + ":block/template_pride_flag12", Constants.MODID + ":block/carpet12"),
        P14(Constants.MODID + ":block/template_pride_flag14", Constants.MODID + ":block/carpet14"),
        P15(Constants.MODID + ":block/template_pride_flag15", Constants.MODID + ":block/carpet15"),
        P16("minecraft:block/template_glazed_terracotta", "block/carpet");

        public final Model modelTemplate;
        public final Model carpetModelTemplate;

        ModelTemplate(String modelTemplate, String carpetModelTemplate) {
            this.modelTemplate = model(modelTemplate, TextureKey.PATTERN);
            this.carpetModelTemplate = model(carpetModelTemplate, TextureKey.WOOL);
        }


        private static Model model(String parent, TextureKey... requiredTextureKeys) {
            return new Model(Optional.of(Identifier.of(parent)), Optional.empty(), requiredTextureKeys);
        }
    }
}
