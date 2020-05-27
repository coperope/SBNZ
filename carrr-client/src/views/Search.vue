<template>
  <div align="center" justify="center">
    <v-col cols="10" align="center" justify="center">
      <v-row class="shadow-lg">
        <v-col cols="1">
          <b-dropdown id="dropdown-1" :text="selectedBrand" placeholder="Brand" class="m-md-2">
            <b-dropdown-item v-for="brand in brands" :key="brand.id">{{brand.name}}</b-dropdown-item>
          </b-dropdown>
        </v-col>
        <v-col cols="1">
          <b-dropdown id="dropdown-1" :text="selectedBrand" placeholder="Brand" class="m-md-2">
            <b-dropdown-item v-for="model in models" :key="model.id">{{model.name}}</b-dropdown-item>
          </b-dropdown>
        </v-col>
        <v-col cols="1">
          <b-dropdown id="dropdown-1" :text="selectedBrand" placeholder="Brand" class="m-md-2">
            <b-dropdown-item v-for="fuel in fuels" :key="fuel.id">{{fuel.name}}</b-dropdown-item>
          </b-dropdown>
        </v-col>
        <v-col cols="1">
          <b-dropdown id="dropdown-1" :text="selectedBrand" placeholder="Brand" class="m-md-2">
            <b-dropdown-item v-for="category in categories" :key="category.id">{{category.name}}</b-dropdown-item>
          </b-dropdown>
        </v-col>
        <v-col cols="1">
          <b-dropdown id="dropdown-1" :text="selectedBrand" placeholder="Brand" class="m-md-2">
            <b-dropdown-item v-for="brand in brands" :key="brand.id">{{brand.name}}</b-dropdown-item>
          </b-dropdown>
        </v-col>
      </v-row>
    </v-col>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      selectedBrand: "Brand",
      brands: null,
      models: null,
      fuels: null,
      categories: null,
      transmissions: null,
      items: null
    };
  },
  watch: {
    selectedBrand(val){
      this.models = val.models;
    }
  },
  mounted() {
    axios
      .get("brand")
      .then(response => {
        console.log(response);
        this.brands = JSON.stringify(response.data);
      })
      .catch(error => {
        console.log(error.response.data);
      });
    axios
      .get("fuel")
      .then(response => {
        this.fuels = JSON.stringify(response.data);
      })
      .catch(error => {
        console.log(error.response.data);
      });
    axios
      .get("category")
      .then(response => {
        this.categories = JSON.stringify(response.data);
      })
      .catch(error => {
        console.log(error.response.data);
      });
    axios
      .get("transmission")
      .then(response => {
        this.transmissions = JSON.stringify(response.data);
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }
};
</script>

<style>
</style>