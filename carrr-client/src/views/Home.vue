<template>
  <b-container class="mt-4">
    <b-jumbotron header="Welcome to CarRR" lead="Best site for Car Rental Recommendations"></b-jumbotron>

    <v-container v-if="this.$store.state.user.customer">
      <b-jumbotron header="Recommended for you based on:" bg-variant="white" text-variant="primary" header-level="1" align="center"></b-jumbotron>
      <v-tabs>
        <v-tab @click="combined">Combined</v-tab>
        <v-tab @click="byPreferences">Preferences</v-tab>
        <v-tab @click="bySearches">Previous searches</v-tab>
        <v-tab @click="byRentals">Previous rentals</v-tab>
      </v-tabs>
      <v-col class="shadow-lg" align="center" justify="center">
        <v-container v-for="vehicle in vehicles" :key="vehicle.id">
          <VehicleCard :vehicle="vehicle" class="mb-2" />
        </v-container>
      </v-col>
    </v-container>
  </b-container>
</template>

<script>
import axios from "axios";
import VehicleCard from "@/components/VehicleCard.vue";

export default {
  name: "Home",
  components: {
    VehicleCard
  },
  data() {
    return {
      vehicles: []
    };
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.$store.state.user = JSON.parse(localStorage.getItem("user"));

      this.combined();
    } else {
      this.$store.state.user = null;
    }
  },
  methods: {
    byPreferences() {
      axios
        .get(`/user/${this.$store.state.user.id}/recommendations/preferences`)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    bySearches() {
      axios
        .get(`/user/${this.$store.state.user.id}/recommendations/searches`)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    byRentals() {
      axios
        .get(`/user/${this.$store.state.user.id}/recommendations/rentals`)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    combined() {
      axios
        .get(`/user/${this.$store.state.user.id}/recommendations/combined`)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>
