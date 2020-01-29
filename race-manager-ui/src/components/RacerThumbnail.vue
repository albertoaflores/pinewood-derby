<template>
    <div class="col-2 text-center racer-info" v-if="racer">
        <div class="racer-picture"><img alt="Pinewood Derby" class="img-thumbnail" src="../assets/default-racer.png"></div>
        <div class="racer-name">{{ racer.name }}</div>
        <div class="racer-status">
            <div v-if="racer.enabled" class="badge racer-ready">
                Ready
            </div>
            <div v-else class="badge racer-notReady">
                Not Ready
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'racerThumbnail',
    props: { 
        racer: {
            type: Object,
            default: function() {
                return {
                    name: '',
                    enabled: false
                }
            }
        }
    }
}
</script>
<style scoped>
.racer-info {
    padding-bottom: 25px;
}
.racer-picture {
    padding-bottom: 5px;
}
.racer-status {
    font-size: 80%;
}

.racer-ready {
    color: #ffffff;
    background-color: green;
}

.racer-notReady {
    color: #ffffff;
    background-color: red;
}
</style>