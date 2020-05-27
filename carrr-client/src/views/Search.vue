<template>
  <div align="center" justify="center" class = "mt-3">
    <v-col class="shadow-lg" cols="10" align="center" justify="center">
      <v-row>
        <v-col cols="2">
          <v-select
            v-model="selectedBrand"
            :items="brands"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Brands"
            multiple
            hint="Pick brands."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedModel"
            :items="models"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Model"
            multiple
            hint="Pick models."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedFuel"
            :items="fuels"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Fuel"
            multiple
            hint="Pick fuels."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedCategory"
            :items="categories"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Category"
            multiple
            hint="Pick categories."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedTag"
            :items="tags"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Tag"
            multiple
            hint="Pick tags."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedTransmission"
            :items="transmissions"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Transmissions."
            multiple
            hint="Pick transmissions."
            persistent-hint
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-select
            v-model="selectedSeatsNo"
            :items="seatsNo"
            :menu-props="{ maxHeight: '400' }"
            label="Seats number."
            multiple
            hint="Pick seats number."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedDoorNo"
            :items="doorNo"
            :menu-props="{ maxHeight: '400' }"
            label="Seats number."
            multiple
            hint="Pick seats number."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="2">
          <v-select
            v-model="selectedFuelConsumptions"
            :items="fuelConsumptions"
            :menu-props="{ maxHeight: '400' }"
            label="Fuel consumption."
            multiple
            hint="Pick fuel consumption."
            persistent-hint
          ></v-select>
        </v-col>
        
      </v-row>
      <v-row align="center" justify="center">
           <b-button variant="outline-dark" @click="search">Search</b-button>
        
        </v-row>
    </v-col>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      selectedBrand: null,
      selectedModel: null,
      selectedCategory: null,
      selectedTransmission: null,
      selectedFuel: null,
      selectedTag: null,
      selectedSeatsNo: null,
      selectedDoorNo: null,
      selectedFuelConsumptions: null,
      brands: null,
      models: null,
      fuels: null,
      categories: null,
      transmissions: null,
      tags: null,
      items: null,
      seatsNo: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17],
      doorNo: [2, 3, 4, 5, 6],
      fuelConsumptions: [
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17
      ]
    };
  },
  watch: {
    selectedBrand(val) {
      this.brands.forEach(element => {
        if (element.name == val) {
          this.models = element.models;
          return;
        }
      });
    }
  },
  methods: {
    search(){

    }
  },
  mounted() {
    axios
      .get("brand")
      .then(response => {
        console.log(response);
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
      .get("category")
      .then(response => {
        this.categories = response.data;
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
    axios
      .get("tag")
      .then(response => {
        this.tags = response.data;
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }
};
</script>

<style>
</style>