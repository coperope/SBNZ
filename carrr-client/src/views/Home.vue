<template>
  <b-container class="mt-4">
    <b-jumbotron header="Welcome to CarRR" lead="Best site for Car Rental Recommendations">
      <p>Coming Soon...</p>
    </b-jumbotron>
  </b-container>
</template>

<script>
export default {
  name: "Home",
  mounted() {
    if (localStorage.getItem("user")) {
      this.$store.state.user = JSON.parse(localStorage.getItem("user"));
    }else{
       this.$store.state.user = null;
    }
  }
};
</script>
