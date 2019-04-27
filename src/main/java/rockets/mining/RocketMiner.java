package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class RocketMiner {
    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);

    private DAO dao;

    public RocketMiner(DAO dao) {
        this.dao = dao;
    }

    /**
     * TODO: to be implemented & tested!
     * Returns the top-k most active rockets, as measured by number of completed launches.
     *
     * @param k the number of rockets to be returned.
     * @return the list of k most active rockets.
     */
    public List<Rocket> mostLaunchedRockets(int k) {
        return null;
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most reliable launch service providers as measured
     * by percentage of successful launches.
     *
     * @param k the number of launch service providers to be returned.
     * @return the list of k most reliable ones.
     */
    public List<LaunchServiceProvider> mostReliableLaunchServiceProviders(int k) {
        return null;
    }

    /**
     * <p>
     * Returns the top-k most recent launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most recent launches.
     */
    public List<Launch> mostRecentLaunches(int k) {
        logger.info("find most recent " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the dominant country who has the most launched rockets in an orbit.
     *
     * @param orbit the orbit
     * @return the country who sends the most payload to the orbit
     */
    public String dominantCountry(String orbit) {
        logger.info("find the dominant country in" + orbit + "orbit");
        return null;}

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k) {
        logger.info("find top " + k +" most expensive launches");
        if(k <= 0){
            throw new IllegalArgumentException("the number k should no less than 1");
        }
        Collection<Launch> launchSet = dao.loadAll(Launch.class);
        if (launchSet.isEmpty()){
            return Collections.emptyList();
        }
        List<Launch> launchList = new ArrayList<>(launchSet);
        Collections.sort(launchList, new Comparator<Launch>() {
            @Override
            public int compare(Launch o1, Launch o2) {
                return o2.getPrice().compareTo(o1.getPrice());
            }
        });
        if (launchList.size()<k){
            return launchList;
        }
        return launchList.subList(0,k);
    }


    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns a list of launch service provider that has the top-k highest
     * sales revenue in a year.
     *
     * @param k the number of launch service provider.
     * @param year the year in request
     * @return the list of k launch service providers who has the highest sales revenue.
     * the launch price * number of launch
     */
    public List<LaunchServiceProvider> highestRevenueLaunchServiceProviders(int k, int year) {
        logger.info("find top " + k +" launch service provider who has the highest revenue");
        if(k <= 0){
            throw new IllegalArgumentException("the number of k should no less than i");
        }
        Collection<Launch> launchSet = dao.loadAll(Launch.class);
        if (launchSet.isEmpty()){
            return Collections.emptyList();
        }
        List<Launch> launchList = new ArrayList<>(launchSet);
        Collection<LaunchServiceProvider> providerCollection = dao.loadAll(LaunchServiceProvider.class);
        List<LaunchServiceProvider> sortList = new ArrayList<>();
        Iterator<LaunchServiceProvider> iterator =  providerCollection.iterator();
        int revenue = 0;
        while (iterator.hasNext()) {
            LaunchServiceProvider lsp = iterator.next();
            Launch l = new Launch();
            int year1 = l.getLaunchDate().getYear();
            if (year == year1){
                revenue = revenue + l.getPrice().intValue();
                lsp.setSalesRevenue(revenue);
                sortList.add(lsp);
            }
        }
        Comparator<LaunchServiceProvider> comparator = (a, b) -> -a.getSalesRevenue()-(b.getSalesRevenue());
        return sortList.stream().sorted(comparator).limit(k).collect(Collectors.toList());


    }
}