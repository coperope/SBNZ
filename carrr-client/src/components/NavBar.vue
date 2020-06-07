<template>
  <b-navbar type="dark" variant="dark">
    <b-container>
      <router-link to="/" tag="b-navbar-brand">CarRR</router-link>
      <template v-if="userSignedIn">
        <b-navbar-nav>
          <router-link to="/search" tag="b-nav-item">Search</router-link>
          <router-link to="/about" tag="b-nav-item" class="mr-5">About</router-link>
          <router-link to="/administration" tag="b-nav-item" class="mr-5">Rule administration</router-link>

          <b-nav-item-dropdown right>
            <template v-slot:button-content>
              <em>{{ userSignedIn.name }} {{ userSignedIn.surname }}</em>
            </template>
            <template v-if="userSignedIn.customer">
              <router-link
                :to="'/preferences/'+this.$store.state.user.id"
                tag="b-dropdown-item"
              >Preferences</router-link>
            </template>
            <template v-else>
              <router-link to="/vehicles" tag="b-dropdown-item">Vehicles</router-link>
            </template>
            <b-dropdown-divider />
            <b-dropdown-item @click="logout">Logout</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </template>
    </b-container>
  </b-navbar>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    logout() {
      localStorage.removeItem("user");
      this.$store.state.user = null;
      this.$router.push("/login");
    }
  },
  computed: {
    userSignedIn() {
      return this.$store.state.user;
    }
  }
};
</script>

<style scoped>
</style>