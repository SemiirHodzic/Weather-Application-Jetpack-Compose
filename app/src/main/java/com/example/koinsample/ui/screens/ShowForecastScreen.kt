package com.example.koinsample.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koinsample.R
import com.example.koinsample.data.dto.Forecast
import com.example.koinsample.ui.theme.KoinSampleTheme
import com.example.koinsample.ui.viewmodel.MainViewModel
import com.example.koinsample.utils.fromApiTime
import com.example.koinsample.utils.loadPreviewResponse
import com.example.koinsample.utils.timeFormatHoursMinutes
import org.joda.time.DateTime

@Composable
fun ShowForecastScreen(
    city: String?,
    viewModel: MainViewModel
) {

    val forecast by viewModel.forecast.observeAsState(null)
    viewModel.getWeatherFor(city = city)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { city?.let { Text(text = "Weather in $it") } }
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                ForecastList(forecast?.list, modifier)
            }
        }
    )
}

@Composable
private fun ForecastList(
    forecasts: List<Forecast>?,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        forecasts?.forEach { dayForecast ->
            item {
                ForecastDayCard(forecast = dayForecast)
            }
        }
    }
}

@Composable
fun ForecastDayCard(
    forecast: Forecast,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MaterialTheme.colors.background,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val date = forecast.dt.fromApiTime()
            SimpleDateDisplay(date = date)
            SimpleTempDisplay(forecast = forecast)
        }
    }
}

@Composable
fun SimpleDateDisplay(date: DateTime) {
    Text(
        text = "${date.dayOfWeek().asShortText} ${date.monthOfYear().asShortText} ${date.dayOfMonth} ${
            date.toString(
                timeFormatHoursMinutes
            )
        }",
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun SimpleTempDisplay(forecast: Forecast) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(
                id = R.string.temp_formatted_abs,
                forecast.main.temp
            ),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview("Forecast screen")
@Preview("Forecast screen darkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewForecastScreen() {
    KoinSampleTheme() {
        Scaffold {
            ForecastList(loadPreviewResponse().list)
        }
    }
}