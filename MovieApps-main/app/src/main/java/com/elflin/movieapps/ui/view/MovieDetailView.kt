package com.elflin.movieapps.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elflin.movieapps.data.DataSource
import com.elflin.movieapps.model.Movie
import com.elflin.movieapps.viewmodel.MovieDetailUiState
import com.elflin.movieapps.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun MovieDetailView(
    movie: Movie,
    onFavClicked: (Movie) -> Unit
){

    var isLikedView by rememberSaveable { mutableStateOf(movie.isLiked) }

    Column {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {

        }

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "User Data ",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(2f)
                    .height(30.dp)
            )


        }

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = "name : ",
                modifier = Modifier.padding(start = 4.dp)
            )


        }
        Text(
            text = "email : ",
            modifier = Modifier.padding(start = 4.dp)
        )

        Text(
            text = "created at : ",
            modifier = Modifier.padding(start = 4.dp)
        )
        Text(
            text = "",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Left
        )

        Button(
            onClick = {
//                isEmailValid = isValidEmail(email)
//                isPasswordValid = isValidPassword(password)
//
//                if (isEmailValid && isPasswordValid){
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            "Data $name saved"
//                        )
//                    }
//                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
//            enabled = name.isNotBlank()&&phone.isNotBlank()&&age.isNotBlank()&&email.isNotBlank()&&password.isNotBlank()
        )
        {
            Text(text = "Log Out")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieDetailPreview(){

    val movieDetailViewModel: MovieDetailViewModel = viewModel()
    movieDetailViewModel.getMovieById(4)

    val status = movieDetailViewModel.movieDetailUiState
    when(status){
        is MovieDetailUiState.Loading -> {}
        is MovieDetailUiState.Success -> {
            MovieDetailView(
                movie = status.data,
                onFavClicked = {}
            )
        }
        is MovieDetailUiState.Error -> {}
    }
}