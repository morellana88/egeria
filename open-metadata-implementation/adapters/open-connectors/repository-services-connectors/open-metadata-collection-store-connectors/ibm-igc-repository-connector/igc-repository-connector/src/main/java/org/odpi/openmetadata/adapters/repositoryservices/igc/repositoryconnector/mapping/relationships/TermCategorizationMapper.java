/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adapters.repositoryservices.igc.repositoryconnector.mapping.relationships;

/**
 * Singleton to map the OMRS "TermCategorization" relationship between IGC "category" and "term" assets.
 */
public class TermCategorizationMapper extends RelationshipMapping {

    private static class Singleton {
        private static final TermCategorizationMapper INSTANCE = new TermCategorizationMapper();
    }
    public static TermCategorizationMapper getInstance() {
        return Singleton.INSTANCE;
    }

    private TermCategorizationMapper() {
        super(
                "category",
                "term",
                "terms",
                "parent_category",
                "TermCategorization",
                "categories",
                "terms"
        );
        addAlternativePropertyFromTwo("referencing_categories");
    }

}
