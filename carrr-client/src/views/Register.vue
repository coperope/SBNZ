<template>
  <v-row align="center" justify="center">
    <v-col cols="6">
      <v-card class="elevation-12">
        <v-toolbar color="light-blue lighten-2" dark>
          <v-toolbar-title>Sign Up</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form v-model="rules.valid">
            <v-text-field
              label="Name"
              name="name"
              prepend-icon="person"
              type="text"
              v-model="user.name"
              :rules="rules.name"
            ></v-text-field>
            <v-text-field
              label="Surname"
              name="surname"
              prepend-icon="person"
              type="text"
              v-model="user.surname"
              :rules="rules.surname"
            ></v-text-field>
            <v-text-field
              label="E-mail"
              name="e-mail"
              prepend-icon="mail"
              type="mail"
              v-model="user.email"
              :rules="rules.email"
            ></v-text-field>
            <v-text-field
              label="Password"
              name="password"
              prepend-icon="lock"
              type="password"
              v-model="user.password"
              :rules="rules.password"
            ></v-text-field>
            <v-text-field
              label="Confirm Password"
              name="password"
              prepend-icon="lock"
              type="password"
              v-model="confirmPassword"
              :rules="rules.confirmPassword"
            ></v-text-field>
            <v-text-field
              label="Phone Number"
              name="phoneNumber"
              prepend-icon="phone"
              type="text"
              v-model="user.phoneNumber"
              :rules="rules.phoneNumber"
            ></v-text-field>
            <v-switch class="ml-3" label="I am a customer." v-model="user.isCustomer"></v-switch>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <router-link to="/login" tag="a" class="ml-4">Sign In</router-link>
          <v-spacer></v-spacer>
          <v-btn class="mr-2" color="light-blue lighten-2" dark @click="register">Sign up</v-btn>
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
      user: {
        name: null,
        surname: null,
        email: null,
        password: null,
        phoneNumber: null,
        isCustomer: true
      },
      confirmPassword: null,
      rules: {
        valid: false,
        name: [v => !!v || "Name is required"],
        surname: [v => !!v || "Surname is required"],
        email: [v => !!v || "E-mail is required"],
        password: [v => !!v || "Password is required"],
        confirmPassword: [
          v => !!v || "Password is required",
          v => this.user.password === v || "Passwords don't match"
        ],
        phoneNumber: [v => !!v || "Phone Numer is required"]
      }
    };
  },
  methods: {
    register() {
      if (this.rules.valid) {
        alert("Validno");
        axios.post("user/register", this.user).then(response => {
          localStorage.setItem("user", JSON.stringify(response.data));
          this.$router.push("/");
        }).catch(error => {
        console.log(error.response.data);
      });
      } else {
        alert("Nevalidno");
      }
    }
  }
};
</script>

<style scoped>
div .elevation-12 {
  margin-top: 20%;
}
</style>