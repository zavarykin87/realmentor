<template>
  <div>
    <h1>Hello from Vue!</h1>
    <p>Counter: {{ count }}</p>
    <button @click="increment">Click me</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      count: 0
    }
  },
  methods: {
    increment() {
      this.count++
    }
  }
}
</script>

<style scoped>
h1 {
  color: #42b983;
}
</style>