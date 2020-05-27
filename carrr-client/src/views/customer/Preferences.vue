<template>
  <h1>Preference</h1>
</template>

<script>
export default {
  mounted() {
    if (localStorage.getItem("user")) {
      this.$store.state.user = JSON.parse(localStorage.getItem("user"));
    } else {
      this.$store.state.user = null;
    }
  }
};
</script>

<style>
</style>