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
                v-model="vehicle.brand"
                :items="brands"
                item-text="name"
                label="Brand"
                hint="Pick brand."
                persistent-hint
              ></v-select>
            </v-col>
            <v-col>
              <v-select
                v-model="vehicle.model"
                :items="models"
                item-text="name"
                label="Model"
                hint="Pick model."
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="vehicle.fuel"
                :items="fuels"
                item-text="name"
                label="Fuel"
                hint="Pick fuel type."
                persistent-hint
              ></v-select>
            </v-col>
            <v-col>
              <v-select
                v-model="vehicle.transmission"
                :items="transmissions"
                item-text="name"
                label="Transmissions."
                hint="Pick transmission type."
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="vehicle.seatsNo"
                :items="seatsNo"
                label="Seats number."
                hint="Pick number of seats."
                persistent-hint
              ></v-select>
            </v-col>
            <v-col>
              <v-select
                v-model="vehicle.doorNo"
                :items="doorNo"
                label="Door number."
                hint="Pick number of doors."
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                v-model="vehicle.power"
                label="Power"
                hint="Pick power of vehicle in KW."
                persistent-hint
                type="number"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-select
                v-model="vehicle.fuelConsumption"
                :items="fuelConsumptions"
                label="Fuel consumption."
                hint="Pick fuel consumption of vehicle."
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                v-model="vehicle.mileageLimit"
                label="Milage limit"
                hint="Pick (-1) if vehicle has no limit."
                persistent-hint
                type="number"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="vehicle.mileagePrice"
                label="Milage price"
                hint="Pick price in $."
                persistent-hint
                type="number"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                label="Width"
                hint="Pick width in cm."
                persistent-hint
                type="number"
                v-model="vehicle.width"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                label="Height"
                hint="Pick height in cm."
                persistent-hint
                type="number"
                v-model="vehicle.height"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                label="Length"
                hint="Pick length in cm."
                persistent-hint
                type="number"
                v-model="vehicle.length"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                label="Weight"
                hint="Pick weight in kg."
                persistent-hint
                type="number"
                v-model="vehicle.weight"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-switch label="AC." v-model="vehicle.ac"></v-switch>
            </v-col>
            <v-col>
              <v-text-field
                v-model="vehicle.cargoVolume"
                label="Cargo volume."
                hint="Pick volume in liters."
                persistent-hint
                type="number"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="vehicle.passengerAreaVolume"
                label="Passenger volume."
                hint="Pick volume in liters."
                persistent-hint
                type="number"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="vehicle.tankVolume"
                label="Tank volume."
                hint="Pick volume in liters."
                persistent-hint
                type="number"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row align="center" justify="center">
            <v-col cols="8">
              <v-text-field
                v-model="feature"
                label="Add extra features."
                hint="Enter some extra features of vehicle."
                persistent-hint
                type="text"
              ></v-text-field>
            </v-col>
            <v-col cols="4">
              <b-button class="ml-10" variant="outline-secondary" @click="addFeature">Add feature</b-button>
            </v-col>
          </v-row>
          <v-row>
            <v-col align="center" justify="center">
              <template v-for="feature in vehicle.features">
                <label :key="feature.id">{{ feature.name }}</label>
                <br :key="feature.id" />
              </template>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                label="Price per day."
                hint="Price in €."
                persistent-hint
                type="number"
                v-model="vehicle.pricePerDay"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="$emit('close-dialog')">Close</v-btn>
        <v-btn color="green darken-1" text @click="saveVehicle">Save</v-btn>
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
      vehicle: {
        brand: null,
        model: null,
        fuel: null,
        transmission: null,
        seatsNo: null,
        doorNo: null,
        fuelConsumption: null,
        ac: null,
        features: [],
        mileageLimit: null,
        mileagePrice: null,
        power: null,
        weight: null,
        cargoVolume: null,
        passengerAreaVolume: null,
        width: null,
        height: null,
        length: null,
        tankVolume: null,
        pricePerDay: null,
        owner: this.$store.state.user
      },
      brands: [],
      models: [],
      fuels: [],
      transmissions: [],
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
      feature: null
    };
  },
  watch: {
    "vehicle.brand": function(val) {

      if (val == null) {
        return;
      }

      this.models = [];

      if (typeof val === "object") {
        val.models.forEach(model => {
          this.models.push(model);
        });
        return;
      }

      let brand = this.brands.filter(x => val?.includes(x.name))[0];

      brand.models.forEach(model => {
        this.models.push(model);
      });
    }
  },
  methods: {
    saveVehicle() {
      this.vehicle.brand = this.brands.filter(x =>
        this.vehicle.brand?.includes(x.name)
      )[0];
      this.vehicle.model = this.models.filter(x =>
        this.vehicle.model?.includes(x.name)
      )[0];
      this.vehicle.fuel = this.fuels.filter(x =>
        this.vehicle.fuel?.includes(x.name)
      )[0];
      this.vehicle.transmission = this.transmissions.filter(x =>
        this.vehicle.transmission?.includes(x.name)
      )[0];
      console.log(this.vehicle);

      axios
        .post("vehicle", this.vehicle)
        .then(() => {
          this.vehicle = {
            brand: null,
            model: null,
            fuel: null,
            transmission: null,
            seatsNo: null,
            doorNo: null,
            fuelConsumption: null,
            ac: null,
            features: [],
            mileageLimit: null,
            mileagePrice: null,
            power: null,
            weight: null,
            cargoVolume: null,
            passengerAreaVolume: null,
            width: null,
            height: null,
            length: null,
            tankVolume: null,
            pricePerDay: null,
            owner: this.$store.state.user
          };
        })
        .catch(error => {
          console.log(error.response.data);
        });
    },
    addFeature() {
      this.vehicle.features.push({
        name: this.feature
      });
      this.feature = null;
    }
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