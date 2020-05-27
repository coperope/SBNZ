<template>
  <v-row align="center" justify="center">
    <v-col cols="6">
      <v-card class="elevation-12">
        <v-toolbar color="light-blue lighten-2" dark>
          <v-toolbar-title>Sign In</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field label="E-mail" name="e-mail" prepend-icon="mail" type="text"></v-text-field>
            <v-text-field label="Password" name="password" prepend-icon="lock" type="password"></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <router-link to="/register" tag="a" class="ml-4">Sign Up</router-link>
          <v-spacer></v-spacer>
          <v-btn class="mr-2" color="light-blue lighten-2" dark>Sign in</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data() {
    return {};
  }
};
</script>

<style scoped>
div .elevation-12 {
  margin-top: 20%;
}
</style>