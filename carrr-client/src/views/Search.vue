<template>
  <div align="center" justify="center" class="mt-3">
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
      <v-row align="center" justify="end" class="text-right mr-2">
        <b-button variant="outline-dark" @click="search">Search</b-button>
      </v-row>
    </v-col>
    <v-container>
      <b-jumbotron header="Results" align="center"></b-jumbotron>
      <v-col class="shadow-lg" align="center" justify="center">
        <v-container v-for="vehicle in vehicles" :key="vehicle.id">
          <VehicleCard :vehicle="vehicle" class="mb-2" />
        </v-container>
      </v-col>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import VehicleCard from "@/components/VehicleCard.vue";
// import Vue from "vue"

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
      models: [],
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
      ],
      searchParam: {
        categories: [],
        tags: [],
        brands: [],
        models: [],
        fuels: [],
        transmissions: [],
        seatsNo: [],
        doorNo: [],
        fuelConsumptions: [],
        customer: {}
      },
      vehicles: []
    };
  },
  watch: {
    selectedBrand(val) {
      this.models = [];
      this.brands.forEach(element => {
        if (element.name == val) {
          this.models.push(...element.models);
          return;
        }
      });
    }
  },
  methods: {
    search() {
      this.searchParam.categories = this.categories.filter(x =>
        this.selectedCategory?.includes(x.name)
      );
      this.searchParam.tags = this.tags.filter(x =>
        this.selectedTag?.includes(x.name)
      );
      this.searchParam.brands = this.brands.filter(x =>
        this.selectedBrand?.includes(x.name)
      );
      this.searchParam.models = this.models.filter(x =>
        this.selectedModel?.includes(x.name)
      );
      this.searchParam.fuels = this.fuels.filter(x =>
        this.selectedFuel?.includes(x.name)
      );
      this.searchParam.transmissions = this.transmissions.filter(x =>
        this.selectedTransmission?.includes(x.name)
      );
      this.searchParam.seatsNo =
        this.selectedSeatsNo == null ? [] : this.selectedSeatsNo;
      this.searchParam.doorNo =
        this.selectedDoorNo == null ? [] : this.selectedDoorNo;
      this.searchParam.fuelConsumptions =
        this.selectedFuelConsumptions == null
          ? []
          : this.selectedFuelConsumptions;

      this.searchParam.customer = {
        id: this.$store.state.user.id,
        email: this.$store.state.user.email,
        password: this.$store.state.user.password,
        name: this.$store.state.user.name,
        surname: this.$store.state.user.surname,
        customer: this.$store.state.user.customer
      }

      axios
        .post("vehicle/search", this.searchParam)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
   components: {
    VehicleCard
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