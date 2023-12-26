package com.example.clonenetflix

import android.os.Bundle
import android.widget.GridLayout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clonenetflix.ui.theme.CloneNetflixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier= Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())

            ) {
                TopAppSection()
                NetflixContentChooser()
                TrendingShows()
                Addmovielist(movietype = "Watch It Again")
                Addmovielist(movietype = "Trending")
                Addmovielist(movietype = "Blockbuster")
                Addmovielist(movietype = "On Screen")
                Addmovielist(movietype = "Trending")
            }
        }
    }
}
@Composable
@Preview
fun TopAppSection(){
    Row (
        modifier= Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.Center,
    ){
        val borderWidth = 4.dp
        Image(painter = painterResource(id = R.drawable.netflixlogo2),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 12.dp)
                .size(60.dp)
                .border(
                    BorderStroke(borderWidth, Color.Red),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.End
        ){
            Image(painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription ="search" ,
                modifier= Modifier
                    .padding(end = 12.dp, top = 10.dp)
                    .size(36.dp)
            )
            Image(painter = painterResource(id = R.drawable.baseline_people_alt_24),
                contentDescription ="profile" ,
                modifier= Modifier
                    .size(36.dp)
                    .padding(end = 12.dp, top = 10.dp)
            )
        }
    }
}
@Composable
@Preview
fun NetflixContentChooser(){
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "TV Shows", fontSize = 20.sp,color=Color.LightGray)
        Text(text = "Movies", fontSize = 20.sp,color=Color.LightGray)
        Row {
            Text(text = "Categories", fontSize = 20.sp,color=Color.LightGray)
            Image(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription ="icon_drop" )
        }
    }
}
@Composable
@Preview
fun TrendingShows(){
    Column(
        modifier= Modifier.fillMaxWidth(),

        ) {
        Image(painter = painterResource(id = R.drawable.p11),
            contentDescription ="Trending",
            modifier= Modifier
                .padding(start = 55.dp)
                .size(280.dp)
        )
        Row (
            modifier= Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 60.dp, end = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Adventure",color=Color.White)
            Text(text = "Thriller",color=Color.White)
            Text(text = "Fiction",color=Color.White)
            Text(text = "Romance",color=Color.White)
        }
        Row (
            modifier= Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 80.dp, end = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column (verticalArrangement = Arrangement.Center
            ){
                Image(painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription ="add logo" ,
                    modifier=Modifier.padding(start = 10.dp))
                Text(text = "My List",color=Color.White)
            }
            Button(onClick = {},
                colors =ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(4.dp)

            ) {
                Text(text = "Play",color=Color.Black)

            }
            Column {
                Image(painter = painterResource(id = R.drawable.baseline_info_24), contentDescription ="info icon" )
                Text(text = "Info",color=Color.White)
            }

        }
    }
}
@Composable
fun Addmovielist(
    movietype:String
){


    Column(
        modifier=Modifier.fillMaxWidth()
    ) {
        Text(text = movietype,
            fontSize =24.sp,
            fontWeight = FontWeight.Bold,
            color=Color.LightGray,
            modifier=Modifier.padding(top = 20.dp, start = 20.dp)
        )
        LazyRow(
            modifier=Modifier.padding(start = 20.dp)
        ){
            itemsIndexed(getRandomMovieList()){
                    index, item ->
                MovieModelUIItem(imageres = item.Image)
            }

        }

    }
}


@Composable
fun MovieModelUIItem(
    imageres:Int

){
    Image(painter = painterResource(id=imageres),
        contentDescription ="lazy_view1",
        modifier= Modifier
            .height(200.dp)
            .width(150.dp)
    )


}
fun getRandomMovieList():List<MovieItemModel>{
    val listofmovies= mutableListOf<MovieItemModel>()
    listofmovies.add(MovieItemModel(R.drawable.p1))
    listofmovies.add(MovieItemModel(R.drawable.p2))
    listofmovies.add(MovieItemModel(R.drawable.p3))
    listofmovies.add(MovieItemModel(R.drawable.p4))
    listofmovies.add(MovieItemModel(R.drawable.p5))
    listofmovies.add(MovieItemModel(R.drawable.p6))
    listofmovies.add(MovieItemModel(R.drawable.p7))
    listofmovies.add(MovieItemModel(R.drawable.p8))
    listofmovies.add(MovieItemModel(R.drawable.p9))
    listofmovies.add(MovieItemModel(R.drawable.p10))
    listofmovies.add(MovieItemModel(R.drawable.p11))

    listofmovies.shuffle()
    return listofmovies

}
data class MovieItemModel(
    val Image:Int
)
