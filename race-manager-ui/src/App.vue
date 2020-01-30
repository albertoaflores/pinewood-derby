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
              <b-nav-item to="/racers" ><font-awesome-icon :icon="['fas', 'users']" /> Racers</b-nav-item>
              <b-nav-item to="/awards" disabled><font-awesome-icon :icon="['fas', 'medal']" /> Awards</b-nav-item>
              <b-nav-item to="/about"><font-awesome-icon :icon="['fas', 'info-circle']" /> About</b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>
          
        </b-collapse>
      </b-navbar>
    </div>   
    <main>
      <router-view/>
    </main>
    <footer>
      <div class="container ">
        <span>{{ displayTime }}</span>
      </div>
    </footer>
  </div>
</template>

<script>
const moment = require('moment')

export default {
  data() {
    return {
      timeJob : null,
      displayTime: null
    }
  },
  methods: {
    updateFooterClock() {
      this.timeJob = setInterval(function () {
        this.displayTime = moment().format("dddd, MMMM Do YYYY, h:mm:ss a")
      }.bind(this), 1000);
    }
  },
  beforeDestroy() {
    clearInterval(this.timeJob)
  },
  created() {
    this.updateFooterClock()
  }
}
</script>

<style scoped>

main {
    padding: 50px 15px 0;
}

footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  /* Set the fixed height of the footer here */
  height: 50px;
  line-height: 50px; /* Vertically center the text there */
  background-color: #f5f5f5;
  text-align: center;
}

footer > .container {
  padding-right: 15px;
  padding-left: 15px;
}
</style>
