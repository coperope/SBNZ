<template>
  <v-row align="center" justify="center">
    <v-col cols="6">
      <v-card class="elevation-12">
        <v-toolbar color="light-blue lighten-2" dark>
          <v-toolbar-title>Sign In</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field label="E-mail" name="e-mail" prepend-icon="mail" type="text" v-model="user.email"></v-text-field>
            <v-text-field label="Password" name="password" prepend-icon="lock" type="password" v-model="user.password"></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <router-link to="/register" tag="a" class="ml-4">Sign Up</router-link>
          <v-spacer></v-spacer>
          <v-btn class="mr-2" color="light-blue lighten-2" dark @click="login">Sign in</v-btn>
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
    };
  },
  methods: {
    login() {
        axios.post("user/login", this.user).then(response => {
          localStorage.setItem("user", JSON.stringify(response.data));
          this.$store.state.user = localStorage.getItem('user');
          console.log(this.$store.state.user)
          this.$router.push("/");
        }).catch(error => {
        console.log(error.response.data);
      });
    }
  }
};
</script>

<style scoped>
div .elevation-12 {
  margin-top: 20%;
}
</style>