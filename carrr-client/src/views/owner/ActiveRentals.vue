<template>
  <v-container align="center" justify="center" class="mt-3">
    <b-jumbotron header="Active rentals" align="center"></b-jumbotron>

    <v-col class="shadow-lg" align="center" justify="center">
      <v-container>
        <v-data-table
          :headers="headers"
          :items="activeRentals"
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
    <FinishRentalDialog
      :dialog="dialog"
      :rental="rental"
      @close-dialog="closeDialog"
      v-if="dialog"
    />
  </v-container>
</template>

<script>
import axios from "axios";
import FinishRentalDialog from "./components/FinishRentalDialog";

export default {
  data: () => ({
    headers: [
      { text: "ID", value: "id" },
      { text: "Date from", value: "dateFrom" },
      { text: "Date to", value: "dateTo" },
      { text: "Customer name", value: "customer.name" },
      { text: "Customer surname", value: "customer.surname" },
      { text: "Customer e-mail", value: "customer.email" },
      { text: "Vehicle ID", value: "vehicle.id" },
      { text: "Vehicle brand", value: "vehicle.brand.name" },
      { text: "Vehicle model", value: "vehicle.model.name" },
      { text: "Finish rental", value: "actions", sortable: false }
    ],
    activeRentals: [],
    dialog: false,
    rental: null
  }),
  methods: {
    openDialog(rental) {
      this.rental = rental;
      this.dialog = true;
    },
    closeDialog() {
      if (this.$store.state.user != null) {
        axios
          .get("rental/active/" + this.$store.state.user.id)
          .then(response => {
            this.activeRentals = response.data;
            this.rental = null;
            this.dialog = false;
          })
          .catch(error => {
            console.log(error.response.data);
            this.rental = null;
            this.dialog = false;
          });
      }
    }
  },
  filters: {
    dateFormat(value) {
      var date = new Date(value);
      return (
        ("0" + date.getHours()).slice(-2) +
        ":" +
        ("0" + date.getMinutes()).slice(-2) +
        " " +
        ("0" + date.getDate()).slice(-2) +
        "/" +
        ("0" + (date.getMonth() + 1)).slice(-2) +
        "/" +
        date.getFullYear()
      );
    }
  },
  components: {
    FinishRentalDialog
  },
  mounted() {
    if (this.$store.state.user != null) {
      axios
        .get("rental/active/" + this.$store.state.user.id)
        .then(response => {
          this.activeRentals = response.data;
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