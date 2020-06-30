<template>
  <v-container align="center" justify="center" class="mt-3">
    <b-jumbotron header="Vehicles above average" align="center"></b-jumbotron>

    <v-col class="shadow-lg" align="center" justify="center">
      <v-container>
        <v-data-table
          :headers="headers"
          :items="vehicles"
          hide-default-footer
          class="elevation-1"
        >
          <template v-slot:item.dateFrom="{ item }">{{ item.dateFrom | dateFormat}}</template>
          <template v-slot:item.dateTo="{ item }">{{ item.dateTo | dateFormat}}</template>
          <template v-slot:item.actions="{ item }">
            <v-btn icon>
              <v-icon color="green darken-2" @click="openDialog(item)">mdi-check</v-icon>
            </v-btn>
          </template>
        </v-data-table>
      </v-container>
    </v-col>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    headers: [
      { text: "ID", value: "id" },
      { text: "Brand", value: "brand.name" },
      { text: "Model", value: "model.name" },
      { text: "Fuel", value: "fuel.name" },
      { text: "Transmission", value: "transmission.name" },
      { text: "Seats number", value: "seatsNo" },
      { text: "Door number", value: "doorNo" },
    ],
    vehicles: [],
  }),
  mounted(){
      if (this.$store.state.user != null) {
        axios
          .get("vehicle/above-average/" + this.$store.state.user.id)
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

<style>
</style>