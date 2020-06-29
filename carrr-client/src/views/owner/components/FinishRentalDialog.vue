<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="60%">
      <v-card>
        <v-card-title>
          <h3>Add malfunctions</h3>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col>
                <v-select
                  v-model="selectedMalfunctions"
                  :items="trivialMalfunctions"
                  item-text="name"
                  label="Trivial malfunctions"
                  multiple
                  hint="Pick trivial malfunctions if there is any."
                  persistent-hint
                ></v-select>
              </v-col>
              <v-col>
                <v-select
                  v-model="selectedMalfunctions"
                  :items="intermediateMalfunctions"
                  item-text="name"
                  label="Intermediate malfunctions"
                  multiple
                  hint="Pick intermediate malfunctions if there is any."
                  persistent-hint
                ></v-select>
              </v-col>
              <v-col>
                <v-select
                  v-model="selectedMalfunctions"
                  :items="seriousMalfunctions"
                  item-text="name"
                  label="Serious malfunctions"
                  multiple
                  hint="Pick serious malfunctions if there is any."
                  persistent-hint
                ></v-select>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red darken-1" text @click="$emit('close-dialog')">Close</v-btn>
          <v-btn color="green darken-1" text @click="finishRental">Finish rental</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  props: ["dialog", "rental"],
  data: () => ({
    malfunctions: [],
    selectedMalfunctions: []
  }),
  computed: {
    trivialMalfunctions() {
      let trivial = [];
      this.malfunctions.forEach(malfunction => {
        if (malfunction.type === "TRIVIAL") {
          trivial.push(malfunction);
        }
      });
      return trivial;
    },
    intermediateMalfunctions() {
      let intermediate = [];
      this.malfunctions.forEach(malfunction => {
        if (malfunction.type === "INTERMEDIATE") {
          intermediate.push(malfunction);
        }
      });
      return intermediate;
    },
    seriousMalfunctions() {
      let serious = [];
      this.malfunctions.forEach(malfunction => {
        if (malfunction.type === "SERIOUS") {
          serious.push(malfunction);
        }
      });
      return serious;
    }
  },
  methods: {
    finishRental() {
      let selectedMalfunctions = [];

      this.selectedMalfunctions.forEach(malfunction => {
        selectedMalfunctions.push(
          this.malfunctions.filter(x => x.name?.includes(malfunction))[0]
        );
      });

      axios
        .post("rental/" + this.rental.id, selectedMalfunctions)
        .then(() => {
          this.$emit("close-dialog");
        })
        .catch(error => {
          console.log(error.response.data);
        });
    }
  },
  mounted() {
    axios
      .get("malfunction")
      .then(response => {
        this.malfunctions = response.data;
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }
};
</script>

<style>
</style>