<template>
  <div id="app">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand to="/">Pinewood Derby</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-navbar-nav>
              <b-nav-item to="/dashboard">Dashboard</b-nav-item>
              <b-nav-item to="/racers" disabled>Racers</b-nav-item>
              <b-nav-item to="/awards" disabled>Awards</b-nav-item>
              <b-nav-item to="/about">About</b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>
          
        </b-collapse>
      </b-navbar>
    </div>   
    <router-view/>
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
