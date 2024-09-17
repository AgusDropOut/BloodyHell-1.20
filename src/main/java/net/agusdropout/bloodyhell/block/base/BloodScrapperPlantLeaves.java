package net.agusdropout.bloodyhell.block.base;

import net.minecraft.util.StringRepresentable;


    public enum BloodScrapperPlantLeaves implements StringRepresentable {
        NONE("none"),
        SMALL("small"),
        LARGE("large");

        private final String name;

        private BloodScrapperPlantLeaves(String size) {
            this.name = size;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }
    }

