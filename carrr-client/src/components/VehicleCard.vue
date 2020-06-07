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
      <v-col v-if="vehicle.owner.isCustomer">
        <v-row>
          <v-col>
            <b-form-rating stars="5" v-model="vehicle.numberOfStars"></b-form-rating>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <b-button variant="primary">Rent</b-button>
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
  </b-card>
</template>

<script>
export default {
    props: ["vehicle"],
    mounted(){
        console.log("Usao");
        console.log(this.vehicle);
    }
};
</script>

<style>
</style>