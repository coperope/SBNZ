<template>
  <v-app id="app">
    <NavBar />
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar.vue";
export default {
  name: "App",
  components: {
    NavBar
  }
};
</script>

<style>
</style>