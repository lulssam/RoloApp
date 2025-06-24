package dam.a50799.prj_roloapp.ui.theme.guide.develop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DevelopStepScreenContent(
    viewModel: DevelopViewModel,
    navController: NavController
) {
    val steps by viewModel.steps.collectAsState()
    val currentIndex by viewModel.currentStepIndex.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val currentStep = steps.getOrNull(currentIndex) ?: run {
        Text("No instructions available...")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = currentStep.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = currentStep.instruction,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.previousStep() },
                enabled = currentIndex > 0
            ) {
                Text("Anterior")
            }

            Text(
                text = "${currentIndex + 1} / ${steps.size}",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Button(
                onClick = { viewModel.nextStep() },
                enabled = currentIndex < steps.size - 1
            ) {
                Text("Próximo")
            }
        }
    }
}