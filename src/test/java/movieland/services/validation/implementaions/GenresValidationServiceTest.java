package movieland.services.validation.implementaions;

import movieland.TestBase;
import movieland.domain.entities.enumerations.Classification;
import movieland.domain.models.service.GenreServiceModel;
import movieland.services.validation.GenresValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GenresValidationServiceTest extends TestBase {

    @Autowired
    private GenresValidationService genresValidationService;

    private GenreServiceModel genreServiceModel;

    @Override
    public void before() {
        genreServiceModel = new GenreServiceModel();
        genreServiceModel.setId(UUID.randomUUID().toString());
        genreServiceModel.setName("Comedy");
        genreServiceModel.setIsAgeRestrictionRequired(true);
        genreServiceModel.setClassification(Classification.A);
    }

    @Override
    protected void setupMockBeansActions() {

    }

    @Test
    public void isValid_WhenNameIsNull_ShouldReturnFalse() {
        genreServiceModel.setName(null);
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenNameIsNotNull_ShouldReturnTrue() {
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertTrue(isValid);
    }

    @Test
    public void isValid_WhenNameIsBlank_ShouldReturnFalse() {
        genreServiceModel.setName(" ".repeat(6));
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenNameLengthIsLessThan3_ShouldReturnFalse() {
        genreServiceModel.setName("Ab");
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenNameLengthIsGreaterThan25_ShouldReturnFalse() {
        genreServiceModel.setName("A".repeat(26));
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenNameLengthIsInRange_ShouldReturnTrue() {
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertTrue(isValid);
    }

    @Test
    public void isValid_WhenClassificationIsNull_ShouldReturnFalse() {
        genreServiceModel.setClassification(null);
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenClassificationIsNotNull_ShouldReturnTrue() {
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertTrue(isValid);
    }

    @Test
    public void isValid_WhenIsAgeRestrictionRequiredIsNull_ShouldReturnFalse() {
        genreServiceModel.setIsAgeRestrictionRequired(null);
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenIsAgeRestrictionRequiredIsNotNull_ShouldReturnTrue() {
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertTrue(isValid);
    }
}