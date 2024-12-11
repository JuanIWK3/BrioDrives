package won.who.briodrives

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PickRideScreen() {
    var userId by remember { mutableStateOf("") }
    var origin by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.search_ride),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "User ID")

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userId,
            onValueChange = { userId = it },
            placeholder = { Text("Enter your user ID") }
        )

        Text(text = "Origin")

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = origin,
            onValueChange = { origin = it },
            placeholder = { Text("e.g. 123 Main St, City, State") }
        )

        Text(text = "Destination")

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = destination,
            onValueChange = { destination = it },
            placeholder = { Text("e.g. 456 Main St, City, State") }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = {
                validate(
                    userId = userId,
                    origin = origin,
                    destination = destination,
                    onInvalidate = {
                        Toast.makeText(
                            context,
                            context.getString(it),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    onValidate = {
                        Toast.makeText(
                            context,
                            "Searching for a ride...",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally), shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = stringResource(id = R.string.search_ride),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

private fun validate(
    userId: String,
    origin: String,
    destination: String,
    onInvalidate: (Int) -> Unit,
    onValidate: () -> Unit
) {
    if (userId.isEmpty()) {
        onInvalidate(R.string.user_id_empty)
        return
    }

    if (origin.isEmpty()) {
        onInvalidate(R.string.origin_empty)
        return
    }

    if (destination.isEmpty()) {
        onInvalidate(R.string.destination_empty)
        return
    }

    onValidate()
}
