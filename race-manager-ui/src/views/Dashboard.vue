<template>
    <div class="dashboard">
        <CurrentHeat />
    </div>
</template>

<script>
// @ is an alias to /src
import CurrentHeat from '@/components/CurrentHeat.vue'

export default {
    name: 'dashboard',
    components: {
        CurrentHeat
    }
}
</script>
