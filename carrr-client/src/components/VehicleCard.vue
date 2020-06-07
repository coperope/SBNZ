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
      <v-col v-if="vehicle.owner==null || vehicle.owner.isCustomer">
        <v-row>
          <v-col>
            <b-form-rating stars="5" v-model="vehicle.numberOfStars"></b-form-rating>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <b-button variant="primary" @click="rent">Rent</b-button>
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

    <v-dialog v-model="dialog" persistent max-width="290">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Open Dialog</v-btn>
      </template>
      <v-card>
        <v-card-title class="headline">Use Google's location service?</v-card-title>
        <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
          <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </b-card>
</template>

<script>
export default {
    props: ["vehicle"],
    data(){
      return{
        dialog: false
      }
    },
    methods: {
      rent(){
        this.dialog = true;
      }
    },
    mounted(){
        console.log("Usao");
        console.log(this.vehicle);
    }
};
</script>

<style>
</style>