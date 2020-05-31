<template>
  <v-dialog v-model="dialog" persistent max-width="60%">
    <v-card>
      <v-card-title>
        <h3>Vehicle</h3>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col>
              <v-select
                :items="brands"
                item-text="name"
                label="Brand"
                hint="Pick brands."
                persistent-hint
              ></v-select>
            </v-col>
            <v-col>
              <v-select
                :items="models"
                item-text="name"
                label="Model"
                multiple
                hint="Pick models."
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                :items="fuels"
                item-text="name"
                label="Fuel"
                hint="Pick fuels."
                persistent-hint
              ></v-select>
            </v-col>
            <v-col>
              <v-select
                :items="transmissions"
                item-text="name"
                label="Transmissions."
                hint="Pick transmissions."
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="$emit('close-dialog')">Close</v-btn>
        <!-- <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn> -->
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  props: ["dialog"],
  data() {
    return {
      brands: [],
      models: [],
      fuels: [],
      transmissions: []
    };
  },
  mounted() {
    axios
      .get("brand")
      .then(response => {
        this.brands = response.data;
      })
      .catch(error => {
        console.log(error.response.data);
      });

    axios
      .get("fuel")
      .then(response => {
        this.fuels = response.data;
      })
      .catch(error => {
        console.log(error.response.data);
      });

    axios
      .get("transmission")
      .then(response => {
        this.transmissions = response.data;
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }
};
</script>

<style>
</style>