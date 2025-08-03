package com.sorushi.invoice.management.user_management.tenancy;

public class TenantContext {

  private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

  private TenantContext() {}

  public static String getTenantId() {
    return CURRENT_TENANT.get();
  }

  public static void setTenantId(String tenantId) {
    CURRENT_TENANT.set(tenantId);
  }

  public static void clear() {
    CURRENT_TENANT.remove();
  }
}
