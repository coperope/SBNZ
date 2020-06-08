<template>
  <b-card
    img-src="https://d3h256n3bzippp.cloudfront.net/x5-40e-white-background-2.jpg"
    img-alt="Image"
    img-left
    img-width="300em"
    img-height="250em"
    class="mb-3"
  >
    <v-row class="ml-5">
      <v-col>
        <v-row>
          <label>
            Brand:
            <strong>{{vehicle.brand.name}}</strong>
          </label>
        </v-row>
        <v-row>
          <label>
            Model:
            <strong>{{vehicle.model.name}}</strong>
          </label>
        </v-row>
        <v-row>
          <label>
            Fuel:
            <strong>{{vehicle.fuel.name}}</strong>
          </label>
        </v-row>
        <v-row>
          <label>
            Transmission:
            <strong>{{vehicle.transmission.name}}</strong>
          </label>
        </v-row>
      </v-col>
      <v-col>
        <v-row>
          <label>
            Power:
            <strong>{{vehicle.power}}</strong> kw
          </label>
        </v-row>
        <v-row>
          <label>
            Mileage limit:
            <template v-if="vehicle.mileageLimit === -1">
              <strong>Unlimited</strong>
            </template>
            <template v-else>
              <strong>{{vehicle.mileageLimit}}</strong> km
            </template>
          </label>
        </v-row>
        <v-row>
          <label>
            Mileage price:
            <strong>{{vehicle.mileagePrice}}</strong> $
          </label>
        </v-row>
        <v-row>
          <label>
            Price per day:
            <strong>{{vehicle.pricePerDay}}</strong> $
          </label>
        </v-row>
      </v-col>
      <v-col v-if="userSignedIn.customer">
        <v-row>
          <v-col>
            <b-form-rating stars="5" v-model="vehicle.numberOfStars"></b-form-rating>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <b-button variant="primary" @click="pickDatesForRent">Pick dates for rent</b-button>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row align="center">
      <label class="ml-8">
        Tags:
        <b-badge
          variant="primary"
          class="ml-1"
          v-for="tag in vehicle.tags"
          :key="tag.id"
        >{{tag.name}}</b-badge>
      </label>
    </v-row>
    <v-row align="center">
      <label class="ml-8">
        Categories:
        <b-badge
          variant="secondary"
          class="ml-1"
          v-for="category in vehicle.categories"
          :key="category.id"
        >{{category.name}}</b-badge>
      </label>
    </v-row>

    <v-dialog v-model="dialog" persistent max-width="40%">
      <v-card>
        <v-card-title class="headline">Pick your date</v-card-title>
        <v-container>
          <v-row>
            <v-col>
              <v-date-picker v-model="dates" range full-width></v-date-picker>
            </v-col>
          </v-row>
        </v-container>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red darken-1" text @click="cancelRentalRequest">Cancel</v-btn>
          <v-btn color="green darken-1" text @click="sendRentalRequest">Rent</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </b-card>
</template>

<script>
import axios from "axios";

export default {
    props: ["vehicle"],
    data(){
      return{
        dialog: false,
        dates: [],
      }
    },
    methods: {
      pickDatesForRent(){
        this.dialog = true;
      },
      cancelRentalRequest(){
        console.log(this.dates);
        this.dates = [];
        this.dialog = false;
      },
      sendRentalRequest(){

        if (this.dates.length != 2) {
          alert("Please select from and to date!")
          return;
        }

        let rental = {
          vehicle: this.vehicle,
          owner: this.vehicle.owner,
          customer: this.$store.state.user,
          dateFrom: this.dates[0],
          dateTo: this.dates[this.dates.length - 1]
        }

        let params = {'Content-Type': "application/json"}
        axios
          .post("rental", rental, params)
          .then(() => {
            this.dialog = false;
          })
          .catch(error => {
            console.log(error.response.data);
          });

      }
    },
    computed: {
      userSignedIn() {
        return this.$store.state.user;
      }
  }
};
</script>

<style>
</style>