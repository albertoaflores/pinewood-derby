<template>
    <div>
        <div v-if="racer">
            <div>Image goes here</div>
            <div>Racer Name: {{ racer.name }}</div>
            <div>Racer Status: {{ racer.enabled }}</div>
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