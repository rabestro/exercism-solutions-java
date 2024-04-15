class Badge {
    public String print(Integer id, String name, String department) {
        var sb = new StringBuilder();
        if (id != null) {
            sb.append("[").append(id).append("] - ");
        }
        sb.append(name).append(" - ");
        if (department != null) {
            sb.append(department.toUpperCase(Locale.ROOT));
        } else {
            sb.append("OWNER");
        }
        return sb.toString();
    }
}
