<template>
  <v-row align="center" justify="center">
    <v-col cols="6">
      <v-card class="elevation-12">
        <v-toolbar color="light-blue lighten-2" dark>
          <v-toolbar-title>Sign Up</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field label="Name" name="name" prepend-icon="person" type="text" v-model="user.name"></v-text-field>
            <v-text-field label="Surname" name="surname" prepend-icon="person" type="text" v-model="user.surname"></v-text-field>
            <v-text-field label="E-mail" name="e-mail" prepend-icon="mail" type="mail" v-model="user.email"></v-text-field>
            <v-text-field label="Password" name="password" prepend-icon="lock" type="password" v-model="user.password"></v-text-field>
            <v-text-field
              label="Confirm Password"
              name="password"
              prepend-icon="lock"
              type="password"
            ></v-text-field>
            <v-text-field label="Phone Number" name="phoneNumber" prepend-icon="phone" type="text" v-model="user.phoneNumber"></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <router-link to="/login" tag="a" class="ml-4">Sign In</router-link>
          <v-spacer></v-spacer>
          <v-btn class="mr-2" color="light-blue lighten-2" dark>Sign up</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      user: {
        name: String,
        surname: String,
        email: String,
        password: String,
        phoneNumber: String
      }
    };
  },
  methods: {}
};
</script>

<style scoped>
div .elevation-12 {
  margin-top: 20%;
}
</style>