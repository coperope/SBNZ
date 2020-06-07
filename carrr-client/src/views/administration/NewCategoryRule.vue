<template>
  <v-row align="center" justify="center">
    <v-col cols="8">
      <v-card class="elevation-12">
        <v-toolbar color="light-blue lighten-2" dark>
          <v-toolbar-title>New Category</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <!-- NAME -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Category name:</v-label>
              </v-col>
              <v-col cols="6">
                <v-text-field name="categoryName" type="text" v-model="categoryName"></v-text-field>
              </v-col>
            </v-row>

            <hr>
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>
                  <b>Car attribute</b>
                </v-label>
              </v-col>
              <v-col cols="3">
                <v-label>
                  <b>Value</b>
                </v-label>
              </v-col>
              <v-col cols="2">
                <v-label>
                  <b>Operation</b>
                </v-label>
              </v-col>
            </v-row>

            <!-- FUEL CONSUMPTION -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Fuel consumption:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field
                  name="fuelConsumption"
                  type="number"
                  v-model="parameters.fuelConsumption"
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.fuelConsumptionOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- LENGTH -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Length:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="length" type="number" v-model="parameters.length"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.lengthOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- HEIGHT -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Height:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="height" type="number" v-model="parameters.height"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.heightOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- WIDTH -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Width:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="width" type="number" v-model="parameters.width"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.widthOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- CargoVolume -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Cargo volume:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="cargoVolume" type="number" v-model="parameters.cargoVolume"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.cargoVolumeOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- passengerAreaVolume -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Passenger area volume:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field
                  name="passengerAreaVolume"
                  type="number"
                  v-model="parameters.passengerAreaVolume"
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.passengerAreaVolumeOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- cargoAndPassengerAreaVolume -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Cargo and passenger area combined:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field
                  name="powerToWeight"
                  type="number"
                  v-model="combinedParameters.cargoAndPassengerAreaVolume"
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="combinedParameters.cargoAndPassengerAreaVolumeOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- seatsNo -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Number of seats:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="seatsNo" type="number" v-model="parameters.seatsNo"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.seatsNoOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- doorNo -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Number of doors:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="doorNo" type="number" v-model="parameters.doorNo"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.doorNoOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- pricePerDay -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Price per day:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="pricePerDay" type="number" v-model="parameters.pricePerDay"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="parameters.pricePerDayOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- powerToWeight -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Power to weight ratio:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field
                  name="powerToWeight"
                  type="number"
                  v-model="combinedParameters.powerToWeight"
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="combinedParameters.powerToWeightOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>

            <!-- features -->
            <v-row align="center" justify="center">
              <v-col cols="5" align="center">
                <v-label>Number of additional features:</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field name="features" type="number" v-model="combinedParameters.features"></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-select
                  v-model="combinedParameters.featuresOperation"
                  :items="operations"
                  :menu-props="{ maxHeight: '400' }"
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="mr-2" color="light-blue lighten-2" dark @click="createRule">Create category</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      operations: ["", "==", ">", ">=", "<", "<="],
      categoryName: null,
      parameters: {
        fuelConsumption: null,
        fuelConsumptionOperation: null,
        length: null,
        lengthOperation: null,
        height: null,
        heightOperation: null,
        width: null,
        widthOperation: null,
        cargoVolume: null,
        cargoVolumeOperation: null,
        passengerAreaVolume: null,
        passengerAreaVolumeOperation: null,
        seatsNo: null,
        seatsNoOperation: null,
        doorNo: null,
        doorNoOperation: null,
        pricePerDay: null,
        pricePerDayOperation: null
      },
      combinedParameters: {
        powerToWeight: null,
        powerToWeightOperation: null,
        cargoAndPassengerAreaVolume: null,
        cargoAndPassengerAreaVolumeOperation: null,
        features: null,
        featuresOperation: null
      }
    };
  },

  methods: {
    createRule() {
      if (this.categoryName === "" || !this.categoryName) {
        alert("Category name is needed");
      }
      let rule = {
        conditions: Array(),
        features: this.combinedParameters.features,
        featureOperation: this.combinedParameters.featureOperation,
        categoryName: this.categoryName.replace(/ /g,"_")
      };

      if (
        this.parameters.fuelConsumptionOperation &&
        this.parameters.fuelConsumption
      ) {
        rule.conditions.push([
          "fuelConsumption",
          this.parameters.fuelConsumptionOperation,
          this.parameters.fuelConsumption
        ]);
      }
      if (this.parameters.lengthOperation && this.parameters.length) {
        rule.conditions.push([
          "length",
          this.parameters.lengthOperation,
          this.parameters.length
        ]);
      }
      if (this.parameters.heightOperation && this.parameters.height) {
        rule.conditions.push([
          "height",
          this.parameters.heightOperation,
          this.parameters.height
        ]);
      }
      if (
        this.parameters.widthOperation &&
        this.parameters.width
      ) {
        rule.conditions.push([
          "width",
          this.parameters.widthOperation,
          this.parameters.width
        ]);
      }
      if (
        this.parameters.cargoVolumeOperation &&
        this.parameters.cargoVolume
      ) {
        rule.conditions.push([
          "cargoVolume",
          this.parameters.cargoVolumeOperation,
          this.parameters.cargoVolume
        ]);
      }
      if (
        this.parameters.passengerAreaVolumeOperation &&
        this.parameters.passengerAreaVolume
      ) {
        rule.conditions.push([
          "passengerAreaVolume",
          this.parameters.passengerAreaVolumeOperation,
          this.parameters.passengerAreaVolume
        ]);
      }
      if (
        this.parameters.seatsNoOperation &&
        this.parameters.seatsNo
      ) {
        rule.conditions.push([
          "seatsNo",
          this.parameters.seatsNoOperation,
          this.parameters.seatsNo
        ]);
      }
      if (
        this.parameters.doorNoOperation &&
        this.parameters.doorNo
      ) {
        rule.conditions.push([
          "doorNo",
          this.parameters.doorNoOperation,
          this.parameters.doorNo
        ]);
      }
      if (
        this.parameters.pricePerDayOperation &&
        this.parameters.pricePerDay
      ) {
        rule.conditions.push([
          "pricePerDay",
          this.parameters.pricePerDayOperation,
          this.parameters.pricePerDay
        ]);
      }


      if (
        this.combinedParameters.powerToWeightOperation &&
        this.combinedParameters.powerToWeight
      ) {
        rule.conditions.push([
          "power*1000/weight",
          this.combinedParameters.powerToWeightOperation,
          this.combinedParameters.powerToWeight
        ]);
      }
      if (
        this.combinedParameters.cargoAndPassengerAreaVolumeOperation &&
        this.combinedParameters.cargoAndPassengerAreaVolume
      ) {
        rule.conditions.push([
          "cargoVolume + passengerAreaVolume",
          this.combinedParameters.cargoAndPassengerAreaVolumeOperation,
          this.combinedParameters.cargoAndPassengerAreaVolume
        ]);
      }

      console.log(rule);
      axios
        .post("rule/categorization", rule)
        .then(response => {
          alert(response.data);
        })
        .catch(error => {
          alert(error.response.data);
        });
    }
  }
};
</script>

<style>
</style>
