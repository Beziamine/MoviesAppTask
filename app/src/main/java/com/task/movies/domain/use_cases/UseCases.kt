package com.task.movies.domain.use_cases


class UseCases(
    val getMoviesDetailsUseCase: GetMoviesDetailsUseCase,
    val addToMyListUseCase: AddToMyListUseCase,
    val deleteAllContentFromMyListUseCase: DeleteAllContentFromMyListUseCase,
    val deleteOneFromMyListUseCase: DeleteOneFromMyListUseCase,
    val getMovieGenresUseCase: GetMovieGenresUseCase,
    val getMyListUseCase: GetMyListUseCase,
    val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    val ifExistsUseCase: IfExistsUseCase
)