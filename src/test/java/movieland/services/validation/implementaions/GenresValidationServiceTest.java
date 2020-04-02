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
        genreServiceModel.setAgeRestriction(12);
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
    public void isValid_WhenAgeRestrictionIsNull_ShouldReturnFalse() {
        genreServiceModel.setAgeRestriction(null);
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertFalse(isValid);
    }

    @Test
    public void isValid_WhenAgeRestrictionIsNotNull_ShouldReturnTrue() {
        boolean isValid = genresValidationService.isValid(genreServiceModel);
        assertTrue(isValid);
    }
}
