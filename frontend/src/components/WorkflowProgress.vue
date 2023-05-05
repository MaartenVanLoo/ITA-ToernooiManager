<template>
  <div class="workflow" style="width:fit-content">
    <div class="workflow-header">
      <h2 class="workflow-title mx-3">
        {{ title }}
      </h2>

    </div>
    <div class="workflow-progress-bar">
      <div class="workflow-progress" :style="{ width: progress + '%' }">
        {{ progress }}%
      </div>
    </div>
    <div class="workflow-body">
      <ul class="workflow-steps">
        <li v-for="step in steps" :key="step.id" class="workflow-step">
          <i :class="['mdi', step.completed ? 'mdi-check-circle' : 'mdi-circle-outline']" :style="{ color: step.completed ? 'green' : 'red' }"></i>
          {{ step.name }}
        </li>
      </ul>
      <div class="workflow-slot">
        <slot name="workflow-slot"></slot>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import '@mdi/font/css/materialdesignicons.css';

interface Step {
  id: number;
  name: string;
  completed: boolean;
}

export default defineComponent({
  name: 'Workflow',
  props: {
    title: {
      type: String,
      required: true,
    },
    steps: {
      type: Array as () => Step[],
      required: true,
    },
  },
  computed: {
    progress(): number {
      const completedSteps = this.steps.filter(step => step.completed).length;
      return Math.round((completedSteps / this.steps.length) * 100);
    },
  },
});
</script>

<style scoped>
.workflow {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
}

.workflow-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}

.workflow-title {
  margin: 0;
}

.workflow-progress-bar {
  width: 100%;
  height: 20px;
  background-color: #eee;
  border-radius: 2px;
  overflow: hidden;
}

.workflow-progress {
  height: 100%;
  background-color: #38c172;
  transition: width 0.5s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
}

.workflow-body {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.workflow-steps {
  margin: 0;
  padding: 0;
  list-style: none;
  flex: 1;
}

.workflow-step {
  display: flex;
  align-items: center;
  padding-left: 3px;
  padding-top: 3px;
  margin-bottom: 5px;
  justify-content: space-between;
}

.workflow-step:not(:last-child) {
  padding-bottom: 5px;
  border-bottom: 1px solid #ddd;
}

.workflow-step i {
  margin-right: 10px;
  font-size: 20px;
}

.workflow-slot {
  margin-left: 20px;
}
</style>
