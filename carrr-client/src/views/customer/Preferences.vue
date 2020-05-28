<template>
  <v-container align="center" justify="center" class="mt-3">
    <b-jumbotron header="Preferences" align="center"></b-jumbotron>
    <v-col class="shadow-lg" align="center" justify="center">
      <v-row>
        <v-col>
          <v-select
            v-model="preferences.categoryList"
            :items="categories"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Category"
            multiple
            hint="Pick categories."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col>
          <v-select
            v-model="preferences.tagList"
            :items="tags"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Tag"
            multiple
            hint="Pick tags."
            persistent-hint
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-select
            v-model="preferences.brandList"
            :items="brands"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Brands"
            multiple
            hint="Pick brands."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col>
          <v-select
            v-model="preferences.modelList"
            :items="models"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Model"
            multiple
            hint="Pick models."
            persistent-hint
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-select
            v-model="preferences.fuelList"
            :items="fuels"
            item-text="name"
            :menu-props="{ maxHeight: '400' }"
            label="Fuel"
            multiple
            hint="Pick fuels."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col>
          <v-select
            v-model="preferences.transmissionList"
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
        <v-col>
          <v-select
            v-model="preferences.seatsNo"
            :items="seatsNo"
            :menu-props="{ maxHeight: '400' }"
            label="Seats number."
            hint="You prefer less or equal seats number."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col>
          <v-select
            v-model="preferences.doorNo"
            :items="doorNo"
            :menu-props="{ maxHeight: '400' }"
            label="Door number."
            hint="You prefer less or equal doors."
            persistent-hint
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-select
            v-model="preferences.fuelConsumption"
            :items="fuelConsumptions"
            :menu-props="{ maxHeight: '400' }"
            label="Fuel consumption."
            hint="You prefer less or equal fuel consumption."
            persistent-hint
          ></v-select>
        </v-col>
        <v-col cols="3">
          <v-switch label="Mileage limit." v-model="preferences.mileageLimit"></v-switch>
        </v-col>
        <v-col cols="3">
          <v-switch label="AC." v-model="preferences.ac"></v-switch>
        </v-col>
      </v-row>
      <v-row>
        <v-col align="center" justify="center">
          <b-button variant="outline-secondary" @click="addPreferences">Add preferences</b-button>
        </v-col>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      preferences: {
        categoryList: [],
        tagList: [],
        brandList: [],
        modelList: [],
        fuelList: [],
        transmissionList: [],
        seatsNo: 4,
        doorNo: 4,
        fuelConsumption: 0,
        mileageLimit: false,
        ac: true
      },
      categories: [],
      tags: [],
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
      ]
    };
  },
  watch: {
    "preferences.brandList": function(val) {
      this.models = [];
      this.brands.forEach(brand => {
        val.forEach(selectedBrand => {
          if (brand.name === selectedBrand) {
            brand.models.forEach(model => {
              this.models.push(model);
            });
          }
        });
      });
    }
  },
  methods: {
    addPreferences() {
      console.log("Krenuo");
      let categories = [];
      this.preferences.categoryList.forEach(category => {
        if (category["name"] != undefined) {
          categories.push(category);
        } else {
          this.categories.forEach(tempCategory => {
            if (category === tempCategory.name) {
              categories.push(tempCategory);
              return;
            }
          });
        }
      });
      this.preferences.categoryList = categories;

      let tags = [];
      this.preferences.tagList.forEach(tag => {
        if (tag["name"] != undefined) {
          tags.push(tag);
        } else {
          this.tags.forEach(tempTag => {
            if (tag === tempTag.name) {
              tags.push(tempTag);
              return;
            }
          });
        }
      });
      this.preferences.tagList = tags;

      let brands = [];
      this.preferences.brandList.forEach(brand => {
        if (brand["name"] != undefined) {
          brands.push(brand);
        } else {
          this.brands.forEach(tempBrand => {
            if (brand === tempBrand.name) {
              brands.push(tempBrand);
              return;
            }
          });
        }
      });
      this.preferences.brandList = brands;

      let models = [];
      this.preferences.modelList.forEach(model => {
        if (model["name"] != undefined) {
          models.push(model);
        } else {
          this.models.forEach(tempModel => {
            if (model === tempModel.name) {
              models.push(tempModel);
              return;
            }
          });
        }
      });
      this.preferences.modelList = models;

      let fuels = [];
      this.preferences.fuelList.forEach(fuel => {
        if (fuel["name"] != undefined) {
          fuels.push(fuel);
        } else {
          this.fuels.forEach(tempFuel => {
            if (fuel === tempFuel.name) {
              fuels.push(tempFuel);
              return;
            }
          });
        }
      });
      this.preferences.fuelList = fuels;

      let transmissions = [];
      this.preferences.transmissionList.forEach(transmission => {
        if (transmission["name"] != undefined) {
          transmissions.push(transmission);
        } else {
          this.transmissions.forEach(tempTransmission => {
            if (transmission === tempTransmission.name) {
              transmissions.push(tempTransmission);
              return;
            }
          });
        }
      });
      this.preferences.transmissionList = transmissions;

      this.preferences.mileageLimit = this.preferences.mileageLimit ? 1 : -1;

      console.log(this.preferences);

      axios
        .post(
          "user/user/" + this.$store.state.user.id + "/preferences",
          this.preferences
        )
        .then(() => {
          if (this.preferences.mileageLimit === 1) {
              this.preferences.mileageLimit = true;
            }else{
              this.preferences.mileageLimit = false;
            }
        })
        .catch(error => {
          console.log(error.response.data);
        });
    }
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.$store.state.user = JSON.parse(localStorage.getItem("user"));

      if (this.$route.params.customerId) {
        axios
          .get("user/user/" + this.$route.params.customerId + "/preferences")
          .then(response => {
            console.log(response.data);
            this.preferences = response.data;
            if (response.data.mileageLimit === 1) {
              this.preferences.mileageLimit = true;
            }else{
              this.preferences.mileageLimit = false;
            }
          })
          .catch(error => {
            alert("Error");
            console.log(error.response.data);
          });
      }

      axios
        .get("category")
        .then(response => {
          this.categories = response.data;
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
    } else {
      this.$store.state.user = null;
    }
  }
};
</script>

<style>
</style>