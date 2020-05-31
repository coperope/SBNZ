import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Search from "../views/Search.vue"
import Preferences from "../views/customer/Preferences.vue";
import OwnerVehicles from "../views/owner/OwnerVehicles.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/preferences",
    name: "Preferences",
    component: Preferences,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/preferences/:customerId",
    name: "Preferences",
    component: Preferences,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/vehicles",
    name: "Vehicles",
    component: OwnerVehicles,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
    meta: {
      requiresAuth: false
    }
  }
];

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {

  var user = null;
  if (localStorage.getItem('user')) {
    user = JSON.parse(localStorage.getItem('user'));
  }

  if (from.path === "/register" && to.path === "/" && user != null) {
    if (user.customer) {
      next('/preferences');
    }
  }

  if (to.meta.requiresAuth == false) {
    next();
  } else if (localStorage.getItem('user')) {
    next();
  } else {
    next('/login');
  }

})

export default router;
