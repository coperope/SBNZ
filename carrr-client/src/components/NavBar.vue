<template>
  <b-navbar type="dark" variant="dark">
    <b-container>
      <router-link to="/" tag="b-navbar-brand">CarRR</router-link>
      <template v-if="userSignedIn">
        <b-navbar-nav>
          <router-link to="/search" tag="b-nav-item">Search</router-link>
          <router-link to="/about" tag="b-nav-item">About</router-link>
        </b-navbar-nav>
      </template>
    </b-container>
  </b-navbar>
</template>

<script>
export default {
  data(){
    return{

    }
  },
  computed: {
    userSignedIn(){
      return localStorage.getItem('user');
    }
  }
};
</script>

<style></style>
