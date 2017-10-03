package org.maeno.example.domain;

public enum ProjectType {

    /**
     * The "PRIVATE" means a closed project.
     */
    PRIVATE {
        @Override
        public String getProjectType() {
            return "PRIVATE";
        }
    },
    /**
     * The "PUBLIC" means a open project.
     */
    PUBLIC {
        @Override
        public String getProjectType() {
            return "PUBLIC";
        }
    },
    /**
     * The "PROTECTED" means a limited project.
     */
    PROTECTED {
        @Override
        public String getProjectType() {
            return "PROTECTED";
        }
    };

    /**
     * Gets project type as String.
     *
     * @return project type.
     */
    public abstract String getProjectType();
}
