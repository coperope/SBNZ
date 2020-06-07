<template>
  <v-container align="center" justify="center" class="mt-3">
    <b-jumbotron header="My vehicles" align="center"></b-jumbotron>

    <b-card class="mb-8" align="center" @click="dialog = true">
      <b-card-body>
        <h4>Add vehicle</h4>
      </b-card-body>
    </b-card>
    <v-col class="shadow-lg" align="center" justify="center">
      <v-container v-for="vehicle in vehicles" :key="vehicle.id">
        <VehicleCard :vehicle="vehicle" class="mb-2" />
      </v-container>
    </v-col>

    <AddVehicleDialog :dialog="dialog" @close-dialog="closeDialog" v-if="dialog" />
  </v-container>
</template>

<script>
import AddVehicleDialog from "@/views/owner/components/AddVehicleDialog.vue";
import VehicleCard from "@/components/VehicleCard.vue";

import axios from "axios";

export default {
  data() {
    return {
      dialog: false,
      vehicles: []
    };
  },
  methods: {
    closeDialog() {
      if (this.$store.state.user != null) {
        axios
          .get("vehicle/" + this.$store.state.user.id)
          .then(response => {
            this.vehicles = response.data;
            this.dialog = false;
          })
          .catch(error => {
            console.log(error.response.data);
          });
      }
    }
  },
  components: {
    AddVehicleDialog,
    VehicleCard
  },
  mounted() {
    if (this.$store.state.user != null) {
      axios
        .get("vehicle/" + this.$store.state.user.id)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(error => {
          console.log(error.response.data);
        });
    }
  }
};
</script>

<style scoped>
div.jumbotron + div.card {
  cursor: pointer;
  border-style: dashed;
}

div.jumbotron + div.card:hover {
  border-width: thick;
}
</style>