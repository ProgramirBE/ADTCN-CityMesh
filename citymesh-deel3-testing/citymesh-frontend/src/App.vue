<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// L'adresse de ton backend CRUD (Port 8083)
const API_URL = 'http://localhost:8083/drones';

const drones = ref([])
const serienummer = ref('')
const model = ref('')

// Charger les drones au démarrage
onMounted(async () => {
  await fetchDrones()
})

async function fetchDrones() {
  try {
    const response = await axios.get(API_URL)
    // Spring Data REST renvoie les données dans _embedded
    if (response.data._embedded) {
      drones.value = response.data._embedded.drones
    }
  } catch (error) {
    console.error("Erreur de connexion:", error)
  }
}

async function addDrone() {
  try {
    await axios.post(API_URL, {
      serienummer: serienummer.value,
      model: model.value,
      status: "Beschikbaar",
      batterijNiveau: 100
    })
    // Vider le champ et recharger la liste
    serienummer.value = ''
    model.value = ''
    await fetchDrones()
  } catch (error) {
    alert("Erreur lors de l'ajout")
  }
}
</script>

<template>
  <div class="container">
    <h1>Citymesh Drones</h1>

    <div class="form-group">
      <input v-model="serienummer" placeholder="Serienummer" id="serienummer-input">
      <input v-model="model" placeholder="Model" id="model-input">
      <button @click="addDrone" id="opslaan-btn">Opslaan</button>
    </div>

    <ul>
      <li v-for="drone in drones" :key="drone._links.self.href">
        {{ drone.serienummer }} - {{ drone.model }}
      </li>
    </ul>
  </div>
</template>

<style>
.container { padding: 20px; font-family: sans-serif; }
.form-group { margin-bottom: 20px; }
input { margin-right: 10px; padding: 5px; }
button { padding: 5px 10px; cursor: pointer; }
</style>