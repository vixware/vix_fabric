package com.vix.common.properties.util;

/**
 * CalculationDataVo 为复杂计算而准备的vo,
 */
public class CalculationDataVo{
		private String key;//时间
		private Double valuea;//原始值
		private Double valueb;//中间和值
		private Double valuec;//状态值
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public Double getValuea() {
			return valuea;
		}
		public void setValuea(Double valuea) {
			this.valuea = valuea;
		}
		public Double getValueb() {
			return valueb;
		}
		public void setValueb(Double valueb) {
			this.valueb = valueb;
		}
		public Double getValuec() {
			return valuec;
		}
		public void setValuec(Double valuec) {
			this.valuec = valuec;
		}
		public CalculationDataVo(String key, Double valuea, Double valueb, Double valuec) {
			super();
			this.key = key;
			this.valuea = valuea;
			this.valueb = valueb;
			this.valuec = valuec;
		}
		public CalculationDataVo() {
			super();
		}
		@Override
		public String toString() {
			return "CalculationDataVo [key=" + key + ", valuea=" + valuea + ", valueb=" + valueb + ", valuec=" + valuec
					+ "]";
		}
	}

