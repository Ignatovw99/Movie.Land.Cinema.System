package movieland.domain.models.service;

import movieland.domain.entities.enumerations.Classification;

public class GenreServiceModel extends BaseServiceModel {

    private String name;

    private Classification classification;

    private Boolean isAgeRestrictionRequired;

    public GenreServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Boolean getIsAgeRestrictionRequired() {
        return isAgeRestrictionRequired;
    }

    public void setIsAgeRestrictionRequired(Boolean isAgeRestrictionRequired) {
        this.isAgeRestrictionRequired = isAgeRestrictionRequired;
    }
}
