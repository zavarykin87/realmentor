<template>
  <!-- Header -->
  <div class="relative bg-emerald-600 md:pt-32 pb-32 pt-12">
    <div class="px-4 md:px-10 mx-auto w-full">
      <div>
        <!-- Card stats -->
        <div class="flex flex-wrap">
          <div class="w-full lg:w-6/12 xl:w-3/12 px-4">
            <card-stats
              statSubtitle="Ваши занятия"
              statTitle="Ближайшие занятий НЕТ"
              statPercent="24.04.2025"
              statPercentColor="text-emerald-500"
              statDescripiron="Заплонированное занятие"
              statIconColor="bg-red-500"
            />
          </div>
          <div class="w-full lg:w-6/12 xl:w-3/12 px-4">
            <card-stats
              statSubtitle="ТРАНСЛЯЦИИ"
              statTitle="Не запланировано"
              statArrow="down"
              statPercent="24.10.2026"
              statPercentColor="text-red-500"
              statDescripiron="В планах"
              statIconName="fas fa-chart-pie"
              statIconColor="bg-orange-500"
            />
          </div>
          <div class="w-full lg:w-6/12 xl:w-3/12 px-4">
            <card-stats
              statSubtitle="БАЛАНС"
              statTitle="924 ₽"
              statArrow="up"
              statPercent="31.12.2222"
              statPercentColor="text-emerald-500"
              statDescripiron="Пополнение не требуется"
              statIconName="fas fa-percent"
              statIconColor="bg-emerald-500"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CardStats from "@/components/Cards/CardStats.vue";

export default {
  components: {
    CardStats,
  },
};
</script>
