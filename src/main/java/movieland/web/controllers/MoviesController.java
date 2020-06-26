package movieland.web.controllers;

import movieland.domain.models.binding.movie.MovieCreateBindingModel;
import movieland.domain.models.binding.movie.MovieUpdateBindingModel;
import movieland.domain.models.service.MovieServiceModel;
import movieland.domain.models.view.movie.MovieDeleteViewModel;
import movieland.domain.models.view.movie.MovieViewModel;
import movieland.services.interfaces.MoviesService;
import movieland.validation.movie.MoviesCreateValidator;
import movieland.validation.movie.MoviesUpdateValidator;
import movieland.web.annotations.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

import static movieland.constants.GlobalConstants.MODEL_NAME;

@Controller
@RequestMapping("/movies")
public class MoviesController extends BaseController {

    private final MoviesService moviesService;

    private final MoviesCreateValidator moviesCreateValidator;

    private final MoviesUpdateValidator moviesUpdateValidator;

    private final ModelMapper modelMapper;

    @Autowired
    public MoviesController(MoviesService moviesService, MoviesCreateValidator moviesCreateValidator, MoviesUpdateValidator moviesUpdateValidator, ModelMapper modelMapper) {
        this.moviesService = moviesService;
        this.moviesCreateValidator = moviesCreateValidator;
        this.moviesUpdateValidator = moviesUpdateValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    @Page(title = "Create Movie", name = "movie/movie-create")
    public ModelAndView createMovie(MovieCreateBindingModel movieCreateBindingModel) {
        return view(movieCreateBindingModel);
    }

    @PostMapping("/create")
    public ModelAndView addMovieConfirm(@ModelAttribute(name = MODEL_NAME) MovieCreateBindingModel movieCreateBindingModel, BindingResult bindingResult) {
        moviesCreateValidator.validate(movieCreateBindingModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return view("movie/movie-create");
        }

        MovieServiceModel movieToCreateServiceModel = modelMapper.map(movieCreateBindingModel, MovieServiceModel.class);
        moviesService.create(movieToCreateServiceModel);

        return redirect("/");
    }

    @GetMapping("/update/{id}")
    @Page(title = "Update Movie", name = "movie/movie-update")
    public ModelAndView updateGenre(@PathVariable String id) {
        MovieServiceModel movieServiceModel = moviesService.findById(id);
        MovieUpdateBindingModel movieToUpdate = modelMapper.map(movieServiceModel, MovieUpdateBindingModel.class);
        return view(movieToUpdate);
    }

    @PostMapping("/update")
    public ModelAndView updateGenreConfirm(@ModelAttribute(MODEL_NAME) MovieUpdateBindingModel movieToUpdate, BindingResult bindingResult) {
        moviesUpdateValidator.validate(movieToUpdate, bindingResult);
        if (bindingResult.hasErrors()) {
            return view("movie/movie-update");
        }
        MovieServiceModel movieToUpdateServiceModel = modelMapper.map(movieToUpdate, MovieServiceModel.class);
        moviesService.update(movieToUpdate.getId(), movieToUpdateServiceModel);
        return redirect("/movies/all");
    }

    @GetMapping("/delete/{id}")
    @Page(title = "Delete Movie", name = "movie/movie-delete")
    public ModelAndView deleteMovie(@PathVariable String id) {
        MovieServiceModel movieServiceModel = moviesService.findById(id);
        MovieDeleteViewModel movieToDelete = modelMapper.map(movieServiceModel, MovieDeleteViewModel.class);
        return view(movieToDelete);
    }

    @PostMapping("/delete")
    public ModelAndView deleteMovieConfirm(@RequestParam String id) {
        MovieServiceModel deletedMovieServiceModel = moviesService.delete(id);
        return redirect("/");
    }

    @GetMapping("/all")
    @Page(title = "All Movies", name = "movie/movies-all")
    public ModelAndView getAllMovies(ModelAndView modelAndView) {
        List<MovieViewModel> allMovies = moviesService.findAll()
                .stream()
                .map(movieServiceModel -> modelMapper.map(movieServiceModel, MovieViewModel.class))
                .collect(Collectors.toUnmodifiableList());

        return view(allMovies);
    }
}
