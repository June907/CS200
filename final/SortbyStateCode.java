class SortbyStateCode implements Comparator<countyData>
{
    public int compare(studentData a, countyData b)
    {
        return a.stateCode - b.stateCode;
    }
    public int reversed(countyData a, countyData b)
    {
        return b.stateCode - a.stateCode;
    }
}
