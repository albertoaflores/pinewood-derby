<template>
  <div id="app">
    <div>
      <b-navbar class="py-0" fixed="top" toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand to="/">
          <font-awesome-icon :icon="['fas', 'car']" />
          Pinewood Derby
        </b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-navbar-nav>
              <b-nav-item to="/dashboard"><font-awesome-icon :icon="['fas', 'desktop']" /> Dashboard</b-nav-item>
              <b-nav-item to="/racers" disabled><font-awesome-icon :icon="['fas', 'users']" /> Racers</b-nav-item>
              <b-nav-item to="/awards" disabled><font-awesome-icon :icon="['fas', 'medal']" /> Awards</b-nav-item>
              <b-nav-item to="/about"><font-awesome-icon :icon="['fas', 'info-circle']" /> About</b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>
          
        </b-collapse>
      </b-navbar>

      <router-view/>
    </div>   
  </div>
</template>

<style>
/* #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
} */

/* #nav {
  padding: 20px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
