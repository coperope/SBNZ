<template>
  <v-container align="center" justify="center" class="mt-3">
    <b-jumbotron header="My vehicles" align="center"></b-jumbotron>

    <b-card class="mb-8" align="center" @click="dialog = true">
      <b-card-body>
        <h4>Add vehicle</h4>
      </b-card-body>
    </b-card>
    <v-col class="shadow-lg" align="center" justify="center">
      <h3>Ovde ce se prikazivati korisnikova vozila</h3>
    </v-col>

   <AddVehicleDialog :dialog="dialog" @close-dialog="closeDialog" v-if="dialog"/>
  </v-container>
</template>

<script>
import AddVehicleDialog from "@/views/owner/components/AddVehicleDialog.vue"

export default {
    data(){
        return{
            dialog: false,
        }
    },
    methods: {
        closeDialog(){
            this.dialog = false;
        }
    },
    components: {
    AddVehicleDialog
  },
};
</script>

<style scoped>
div.jumbotron + div.card {
  cursor: pointer;
  border-style: dashed;
}

div.jumbotron + div.card:hover {
  border-width: thick;
}
</style>